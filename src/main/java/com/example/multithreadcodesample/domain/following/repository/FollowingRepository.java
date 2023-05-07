package com.example.multithreadcodesample.domain.following.repository;

import com.example.multithreadcodesample.domain.following.model.entity.Following;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FollowingRepository extends JpaRepository<Following, Long> {
    Optional<Following> findFollowingById(Long id);
}
