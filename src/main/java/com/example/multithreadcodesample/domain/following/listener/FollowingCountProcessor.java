package com.example.multithreadcodesample.domain.following.listener;

import com.example.multithreadcodesample.domain.user.model.entity.User;
import com.example.multithreadcodesample.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor
public class FollowingCountProcessor {
    private final UserRepository userRepository;

    public void follow(Long followerId, Long followingId){
        updateFollowing(followerId, followingId, 1);
    }

    public void unfollow(Long followerId, Long followingId){
        updateFollowing(followerId, followingId, -1);
    }

    private void updateFollowing(Long followerId, Long followingId, int addValue) {
        final Optional<User> followerOptional =
                userRepository.findUserByIdAndIsEnabledTrue(followerId);
        final Optional<User> followingOptional =
                userRepository.findUserByIdAndIsEnabledTrue(followingId);

        if (followerOptional.isPresent() && followingOptional.isPresent()) {
            User follower = followerOptional.get();
            AtomicInteger followerFollowing = new AtomicInteger(follower.getFollowing());

            User following = followingOptional.get();
            AtomicInteger followingFollower = new AtomicInteger(following.getFollower());

            follower.setFollowing(followerFollowing.addAndGet(addValue));
            following.setFollower(followingFollower.addAndGet(addValue));

            userRepository.save(follower);
            userRepository.save(following);
        } else {
            log.warn("Not support user id: {}, {}", followerId, followingId);
        }
    }

}
