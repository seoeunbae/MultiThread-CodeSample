package com.example.multithreadcodesample.domain.like.listener;

import com.example.multithreadcodesample.domain.like.model.entity.Like;

public interface LikeEventListener {
    void onLike(Like like);

    void onUnLike(long id, long targetId, Like.TargetType targetType);
}
