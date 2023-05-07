package com.example.multithreadcodesample.domain.following.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowingCountProcessListener implements FollowingEventListener{
    private final FollowingCountProcessor processor;

    @Override
    public void onFollow(Long followerId, Long followingId) {
        processor.follow(followerId, followingId);
    }

    @Override
    public void onUnFollow(Long followerId, Long followingId) {
        processor.unfollow(followerId, followingId);
    }
}
