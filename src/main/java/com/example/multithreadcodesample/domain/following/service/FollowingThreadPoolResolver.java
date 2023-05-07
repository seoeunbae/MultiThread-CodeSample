package com.example.multithreadcodesample.domain.following.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@Component
public class FollowingThreadPoolResolver {

    private final Map<Boolean, Executor> executorMap = new HashMap<>();

    @Autowired
    public FollowingThreadPoolResolver() {
        executorMap.put(true, createThreadPool(16, 32, 16, "FollowUserExecutor"));
        executorMap.put(false, createThreadPool(16, 32, 16, "UnfollowUserExecutor"));
    }

    private static Executor createThreadPool(int corePoolSize, int maxPoolSize, int queueCapacity, String threadNamePrefix) {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(corePoolSize);
        threadPool.setMaxPoolSize(maxPoolSize);
        threadPool.setQueueCapacity(queueCapacity);
        threadPool.setThreadNamePrefix(threadNamePrefix);
        threadPool.initialize();

        return threadPool;
    }

    public Executor get(Boolean isFollowing) {
        return executorMap.get(isFollowing);
    }
}
