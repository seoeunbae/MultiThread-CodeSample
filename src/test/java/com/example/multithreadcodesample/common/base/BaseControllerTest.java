package com.example.multithreadcodesample.common.base;

import com.example.multithreadcodesample.domain.user.model.entity.User;
import com.example.multithreadcodesample.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(
        locations = "classpath:application.properties")
public class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected UserRepository userRepository;

    public User createMockUser1(){
        User user = new User("TEST-USER01", 0, 0);
        return userRepository.save(user);
    }

    public User createMockUser2(){
        User user = new User("TEST-USER02", 0, 0);
        return userRepository.save(user);
    }




}
