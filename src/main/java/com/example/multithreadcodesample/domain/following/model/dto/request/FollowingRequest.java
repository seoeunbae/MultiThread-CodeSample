package com.example.multithreadcodesample.domain.following.model.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class FollowingRequest {
    private long userId;
    private long followingUserId;
}
