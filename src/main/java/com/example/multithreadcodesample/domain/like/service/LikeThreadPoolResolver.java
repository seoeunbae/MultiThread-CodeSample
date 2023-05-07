package com.example.multithreadcodesample.domain.like.service;

import com.example.multithreadcodesample.domain.like.model.entity.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@Component
public class LikeThreadPoolResolver {

    private final Map<Like.TargetType, Executor> executorMap = new HashMap<>();

    @Autowired
    public LikeThreadPoolResolver(@Qualifier("defaultAsyncThreadPool") Executor defaultAsyncThreadPool){
        Arrays.stream(Like.TargetType.values()).forEach(targetType -> {
            switch (targetType) {
                case STYLING :
                    executorMap.put(targetType, createThreadPool(16, 32, 16, "StylingLikeExecutor"));
                    break;
                case ASSET:
                    executorMap.put(targetType, createThreadPool(16,32,16,"AssetLikeExecutor"));
                    break;
                case BRAND:
                    executorMap.put(targetType, createThreadPool(16,32,16,"BrandLikeExecutor"));
                    break;
                default:
                    executorMap.put(targetType, defaultAsyncThreadPool);
            }
        });
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

    public Executor get(Like.TargetType key) {
        return executorMap.get(key);
    }
}
