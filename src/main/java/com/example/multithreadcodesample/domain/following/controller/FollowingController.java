package com.example.multithreadcodesample.domain.following.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowingController {

    @PostMapping("/follow")
    public void follow(){

    }

    @PostMapping("/unfollow")
    public void unfollow(){

    }
}
