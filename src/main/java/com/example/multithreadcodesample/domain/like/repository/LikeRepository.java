package com.example.multithreadcodesample.domain.like.repository;

import com.example.multithreadcodesample.domain.like.model.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndTargetIdAndTargetType(long id, long targetId, Like.TargetType targetType);
}
