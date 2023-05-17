package com.example.multithreadcodesample.domain.like.service;

import com.example.multithreadcodesample.domain.like.listener.LikeEventListener;
import com.example.multithreadcodesample.domain.like.model.entity.Like;
import com.example.multithreadcodesample.domain.like.repository.LikeRepository;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import com.example.multithreadcodesample.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;

    private final UserRepository userRepository;
    private final List<LikeEventListener> listeners;
    private final LikeThreadPoolResolver threadPoolResolver;

    public void like(long userId, long targetId, Like.TargetType targetType) {
        Optional<Like> optionalLike = likeRepository.findByUserIdAndTargetIdAndTargetType(userId, targetId, targetType);

        if(optionalLike.isPresent()){
            final Like like = optionalLike.get();

            if(!like.getIsEnabled()){
                like.setIsEnabled(true);
                likeRepository.save(like);

                CompletableFuture.runAsync(
                        () -> listeners.forEach(listener -> listener.onLike(like)),
                        threadPoolResolver.get(targetType)
                );
            }
        } else {
            User user = userRepository.findUserByIdAndIsEnabledTrue(userId).orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));
            Like like = likeRepository.save(new Like(user, targetId, targetType));
            CompletableFuture.runAsync(
                    () -> listeners.forEach(listener -> listener.onLike(like)),
                    threadPoolResolver.get(targetType)
            );
        }

    }

    public void unlike(long userId, long targetId, Like.TargetType targetType) {
        likeRepository.findByUserIdAndTargetIdAndTargetType(userId, targetId, targetType)
                .ifPresent(like -> {
                    if (like.getIsEnabled()) {
                        like.setIsEnabled(false);
                        likeRepository.save(like);

                        CompletableFuture.runAsync(
                                () -> listeners.forEach(listener -> listener.onUnLike(userId, targetId, targetType)),
                                threadPoolResolver.get(targetType)
                        );
                    }
                });
    }
}
