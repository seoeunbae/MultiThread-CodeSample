package com.example.multithreadcodesample.domain.following;

import com.example.multithreadcodesample.common.base.BaseControllerTest;
import com.example.multithreadcodesample.domain.following.model.entity.Following;
import com.example.multithreadcodesample.domain.following.repository.FollowingRepository;
import com.example.multithreadcodesample.domain.user.model.entity.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class FollowingTest extends BaseControllerTest {
    private long INIT_ID = 1;
    @Autowired
    private FollowingRepository followingRepository;

    @AfterEach
    public void tearDown(){
        followingRepository.deleteAll();
    }

    private void createFollowing(User user, User followingUser){
        Following following1 = new Following(user, followingUser);
        Following following2 = new Following(followingUser, user);
        followingRepository.save(following1);
        followingRepository.save(following2);
    }


    @Test
    @DisplayName("팔로잉 - 성공")
    public void followingUser() throws Exception {
        User user1 = createMockUser1(); //1

        User user2 = createMockUser2(); //2

        System.out.println(user1.getId());
        System.out.println(user2.getId());
        mockMvc.perform(MockMvcRequestBuilders.post("/follow/1/2").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("언팔로잉 - 성공")
    public void unfollowingUser() throws Exception {
        createFollowing(createMockUser1(), createMockUser2());

        mockMvc.perform(MockMvcRequestBuilders.post("/unfollow/4/3").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
