package com.example.multithreadcodesample.domain.like.controller;

import com.example.multithreadcodesample.domain.like.model.entity.Like;
import com.example.multithreadcodesample.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;


    @PostMapping("/like/{user-id}/{target-type}/{target-id}")
    public void like(
            @PathVariable(name = "target-type") final Like.TargetType targetType,
            @PathVariable(name = "target-id") final long targetId,
            @PathVariable(name = "user-id") final long userId
    ) {
        likeService.like(userId, targetId, targetType);
    }

    @PostMapping("/unlike/{user-id}/{target-type}/{target-id}")
    public void unlike(
            @PathVariable(name = "target-type") final Like.TargetType targetType,
            @PathVariable(name = "target-id") final long targetId,
            @PathVariable(name = "user-id") final long userId
    ){
        likeService.unlike(userId, targetId, targetType);
    }
}
