package com.josue;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by Josue on 11/07/2016.
 */
@Component
public class AsyncControl {

    private static final Logger logger = Logger.getLogger(AsyncControl.class.getName());

    @Async
    public void executeAsync(){
        try {
            logger.info(":: Started ::");
            Thread.sleep(5000);
            logger.info(":: Finished ::");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
