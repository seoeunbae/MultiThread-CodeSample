package com.example.multithreadcodesample.domain.following.controller;

import com.example.multithreadcodesample.domain.following.service.FollowingService;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowingController {
    private final FollowingService followingService;
    @PostMapping("/follow/{user-id}/{following-user-id}")
    @ResponseBody
    public void follow(@PathVariable(name = "following-user-id") final Long followingUserId,
                       @PathVariable(name = "user-id") final Long userId
    ){
        followingService.following(followingUserId, userId);
    }

    @PostMapping("/unfollow/{user-id}/{unfollowing-user-id}")
    @ResponseBody
    public void unfollow(@PathVariable(name = "unfollowing-user-id") final Long unfollowingUserId,
                         @PathVariable(name = "user-id") final Long userId
    ){
        followingService.unfollowing(unfollowingUserId, userId);
    }
}
