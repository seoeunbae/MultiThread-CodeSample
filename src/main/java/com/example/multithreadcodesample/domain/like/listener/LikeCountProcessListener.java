package com.example.multithreadcodesample.domain.like.listener;

import com.example.multithreadcodesample.domain.like.model.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeCountProcessListener implements LikeEventListener{

    private final LikeCountProcessor processor;

    @Override
    public void onLike(Like like) {
        processor.increment(like.getTargetId(),  like.getTargetType());
    }

    @Override
    public void onUnLike(long id, long targetId, Like.TargetType targetType) {
        processor.decrement(targetId, targetType);
    }
}
