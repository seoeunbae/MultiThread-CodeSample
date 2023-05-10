package com.example.multithreadcodesample.domain.following.service;

import com.example.multithreadcodesample.domain.following.listener.FollowingEventListener;
import com.example.multithreadcodesample.domain.following.model.entity.Following;
import com.example.multithreadcodesample.domain.following.repository.FollowingRepository;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import com.example.multithreadcodesample.domain.user.repository.UserRepository;
import com.example.multithreadcodesample.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.procedure.NoSuchParameterException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowingService {
    private final UserRepository userRepository;
    private final FollowingRepository followingRepository;

    private final FollowingThreadPoolResolver threadPoolResolver;

    private final  List<FollowingEventListener> listeners;

    public void following(Long followingUserId, Long userId){
        Optional<Following> optionalFollowing = followingRepository.findFollowingById(followingUserId);

        if(optionalFollowing.isPresent()){
            final Following following = optionalFollowing.get();

            if(!following.getIsEnabled()){
                following.setIsEnabled(true);
                followingRepository.save(following);

                CompletableFuture.runAsync(
                        () -> listeners.forEach(listener -> listener.onFollow(userId, followingUserId)),
                        threadPoolResolver.get(true)
                );
            }
        } else {
            final User followingUser = userRepository.findUserByIdAndIsEnabledTrue(followingUserId).orElseThrow(() -> new NoSuchParameterException("해당하는 유저아이디가 존재하지않습니다."));
            final User user = userRepository.findUserByIdAndIsEnabledTrue(userId).orElseThrow(() -> new NoSuchParameterException("해당하는 유저아이디가 존재하지않습니다."));
           followingRepository.save(new Following(user, followingUser));
           CompletableFuture.runAsync(
                   () -> listeners.forEach( listener -> listener.onFollow(userId, followingUserId)),
                   threadPoolResolver.get(true)
           );
        }
    }

    public void unfollowing(Long unfollowingUserId, Long userId){
       Optional<Following> optionalFollowing = followingRepository.findFollowingById(unfollowingUserId);

        if(optionalFollowing.isPresent()){
            final Following following = optionalFollowing.get();

            if(following.getIsEnabled()){
                following.setIsEnabled(false);
                followingRepository.save(following);

                CompletableFuture.runAsync(
                        () -> listeners.forEach(listener -> listener.onUnFollow(userId, unfollowingUserId)),
                                threadPoolResolver.get(false)
                );
            }
        } else throw new RuntimeException("해당 팔로잉은 존재하지 않습니다.");
    }
}
