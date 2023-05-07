package com.example.multithreadcodesample.domain.following.listener;

import com.example.multithreadcodesample.domain.user.model.entity.User;
import com.example.multithreadcodesample.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
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
            final User follower = followerOptional.get();
            final User following = followingOptional.get();

            follower.setFollowing(follower.getFollowing() + addValue);
            following.setFollower(following.getFollower() + addValue);

            userRepository.save(follower);
            userRepository.save(following);
        } else {
            log.warn("Not support user id: {}, {}", followerId, followingId);
        }
    }

}
