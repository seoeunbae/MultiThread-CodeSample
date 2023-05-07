package com.example.multithreadcodesample.domain.like.controller;

import com.example.multithreadcodesample.domain.like.model.entity.Like;
import com.example.multithreadcodesample.domain.like.service.LikeService;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;


    @PostMapping("/like/{target-type}/{target-id}")
    public void like(
            @PathVariable(name = "target-type") final Like.TargetType targetType,
            @PathVariable(name = "target-id") final long targetId,
            @AuthenticationPrincipal final User user
    ){
        likeService.like(user, targetId, targetType);
    }

    @PostMapping("/unlike/{target-type}/{target-id}")
    public void unlike(
            @PathVariable(name = "target-type") final Like.TargetType targetType,
            @PathVariable(name = "target-id") final long targetId,
            @AuthenticationPrincipal final User user
    ){
        likeService.unlike(user, targetId, targetType);
    }
}
