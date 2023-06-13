package com.example.multithreadcodesample.domain.following.model.dto.response;

import lombok.*;

@Builder
@EqualsAndHashCode
@Setter
@AllArgsConstructor
public class FollowingResponse {
    private long userId;
    private long followingUserId;
}
