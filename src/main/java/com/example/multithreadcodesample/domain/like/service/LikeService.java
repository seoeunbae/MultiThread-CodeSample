package com.example.multithreadcodesample.domain.like.service;

import com.example.multithreadcodesample.domain.like.model.entity.Like;
import com.example.multithreadcodesample.domain.like.repository.LikeRepository;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    public void like(User user, long targetId, Like.TargetType targetType) {
        Optional<Like> optionalLike = likeRepository.findByUserIdAndTargetIdAndTargetType(user.getId(), targetId, targetType);


    }

    public void unlike(User user, long targetId, Like.TargetType targetType) {

    }
}
