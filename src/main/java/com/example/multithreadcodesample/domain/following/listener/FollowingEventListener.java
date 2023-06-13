package com.example.multithreadcodesample.domain.following.listener;

public interface FollowingEventListener {

    void onFollow(Long followerId, Long followingId);

    void onUnFollow(Long followerId, Long followingId);
}
