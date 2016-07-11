package com.josue;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Josue on 11/07/2016.
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {

    private static final Logger logger = Logger.getLogger(SpringAsyncConfig.class.getName());

    //This will configure the executorservice, although is not mandatory to have

    @Override
    public Executor getAsyncExecutor() {
        int size = 5;
        logger.log(Level.INFO, ":: CONFIGURING EXECUTORSERVICE - {0} THREADS ::", size);
        return Executors.newFixedThreadPool(size);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> logger.log(Level.SEVERE, "Uncaught async error", ex);
    }
}