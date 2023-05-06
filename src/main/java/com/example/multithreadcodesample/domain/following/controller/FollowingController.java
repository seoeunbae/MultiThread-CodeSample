package com.example.multithreadcodesample.domain.following.controller;

import com.example.multithreadcodesample.domain.following.service.FollowingService;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowingController {
    private final FollowingService followingService;
    @PostMapping("/follow/{following-user-id}")
    public void follow(@PathVariable(name = "following-user-id") final Long followingUserId,
                       @AuthenticationPrincipal User user
    ){
        followingService.following(followingUserId, user);
    }

    @PostMapping("/unfollow")
    public void unfollow(@PathVariable(name = "unfollowing-user-id") final Long unfollowingUserId,
                         @AuthenticationPrincipal User user
    ){
        followingService.unfollowing(unfollowingUserId, user);
    }
}
