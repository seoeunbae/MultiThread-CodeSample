package com.example.multithreadcodesample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public Executor defaultAsyncThreadPool(){
        // config ref: https://www.baeldung.com/java-threadpooltaskexecutor-core-vs-max-poolsize
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(32); //계속 살아있는 최소 스레드 개수
        threadPool.setMaxPoolSize(128); //생성가능한 최대 스레드 개수
        threadPool.setQueueCapacity(1024 * 10); //해당 큐를 초과하면 (MaxPoolSize이내로)추가 스레드를 생성한다.
        threadPool.setThreadNamePrefix("AsyncExecutor");
        return threadPool;
    }
}
