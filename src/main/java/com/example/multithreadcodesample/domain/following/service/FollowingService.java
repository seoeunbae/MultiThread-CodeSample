package com.example.multithreadcodesample.domain.following.service;

import com.example.multithreadcodesample.domain.following.model.entity.Following;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import com.example.multithreadcodesample.domain.user.repository.UserRepository;
import com.example.multithreadcodesample.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowingService {

    UserRepository userRepository;

    public void following(Long followingUserId, User user){
        User followingUser = userRepository.findUserById(followingUserId).orElseThrow(()->new RuntimeException("해당사용자가 존재하지않습니다."));
        Following following = new Following(user, followingUser );
    }

    public void unfollowing(Long unfollowingUserId, User user){

    }


}
