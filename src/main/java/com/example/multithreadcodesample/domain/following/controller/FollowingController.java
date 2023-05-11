package com.example.multithreadcodesample.domain.following.controller;

import com.example.multithreadcodesample.domain.following.model.dto.request.FollowingRequest;
import com.example.multithreadcodesample.domain.following.service.FollowingService;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowingController {
    private final FollowingService followingService;
    @PostMapping("/follow")
    @ResponseBody
    public void follow(
            @RequestBody FollowingRequest request
   ){
        followingService.following(request.getFollowingUserId(), request.getUserId());
    }

    @PostMapping("/unfollow")
    @ResponseBody
    public void unfollow(
            @RequestBody FollowingRequest request
    ){
        followingService.unfollowing(request.getFollowingUserId(), request.getUserId());
    }
}
