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

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class FollowingService {
    UserRepository userRepository;
    FollowingRepository followingRepository;

    FollowingThreadPoolResolver threadPoolResolver;

    List<FollowingEventListener> listeners;

    public void following(Long followingUserId, User user){
        Optional<Following> optionalFollowing = followingRepository.findFollowingById(followingUserId);

        if(optionalFollowing.isPresent()){
            final Following following = optionalFollowing.get();

            if(!following.getIsEnabled()){
                following.setIsEnabled(true);
                followingRepository.save(following);

                CompletableFuture.runAsync(
                        () -> listeners.forEach(listener -> listener.onFollow(user.getId(), followingUserId)),
                        threadPoolResolver.get(true)
                );
            }
        } else {
            final User followingUser = userRepository.findUserByIdAndIsEnabledTrue(followingUserId).orElseThrow(() -> new NoSuchParameterException("해당하는 유저아이디가 존재하지않습니다."));
           followingRepository.save(new Following(user, followingUser));
           CompletableFuture.runAsync(
                   () -> listeners.forEach( listener -> listener.onFollow(user.getId(), followingUserId)),
                   threadPoolResolver.get(true)
           );
        }
    }

    public void unfollowing(Long unfollowingUserId, User user){
       Optional<Following> optionalFollowing = followingRepository.findFollowingById(unfollowingUserId);

        if(optionalFollowing.isPresent()){
            final Following following = optionalFollowing.get();

            if(following.getIsEnabled()){
                following.setIsEnabled(false);
                followingRepository.save(following);

                CompletableFuture.runAsync(
                        () -> listeners.forEach(listener -> listener.onUnFollow(user.getId(), unfollowingUserId)),
                                threadPoolResolver.get(false)
                );
            }
        }
    }
}
