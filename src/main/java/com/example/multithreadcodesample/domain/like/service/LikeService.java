package com.example.multithreadcodesample.domain.like.service;

import com.example.multithreadcodesample.domain.like.listener.LikeEventListener;
import com.example.multithreadcodesample.domain.like.model.entity.Like;
import com.example.multithreadcodesample.domain.like.repository.LikeRepository;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final List<LikeEventListener> listeners;
    private final LikeThreadPoolResolver threadPoolResolver;

    public void like(User user, long targetId, Like.TargetType targetType) {
        Optional<Like> optionalLike = likeRepository.findByUserIdAndTargetIdAndTargetType(user.getId(), targetId, targetType);

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
            Like like =likeRepository.save(new Like(user, targetId, targetType));
            CompletableFuture.runAsync(
                    () -> listeners.forEach(listener -> listener.onLike(like)),
                    threadPoolResolver.get(targetType)
            );
        }

    }

    public void unlike(User user, long targetId, Like.TargetType targetType) {
        likeRepository.findByUserIdAndTargetIdAndTargetType(user.getId(), targetId, targetType)
                .ifPresent(like -> {
                    if (like.getIsEnabled()) {
                        like.setIsEnabled(false);
                        likeRepository.save(like);

                        CompletableFuture.runAsync(
                                () -> listeners.forEach(listener -> listener.onUnLike(user.getId(), targetId, targetType)),
                                threadPoolResolver.get(targetType)
                        );
                    }
                });
    }
}
