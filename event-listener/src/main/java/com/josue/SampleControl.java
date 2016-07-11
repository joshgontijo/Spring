package com.josue;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by Josue on 11/07/2016.
 */
@Component
public class SampleControl {

    private static final Logger logger = Logger.getLogger(SampleControl.class.getName());

    @EventListener
    public void stringListener(String message) {
        logger.info(":: Received message: '" + message + "' ::");
    }

    @Async
    @EventListener
    public void asyncStringListener(String message) {
        try {
            Thread.sleep(5000);
            logger.info(":: Async String listener (@EnableAsync required): '" + message + "' ::");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @EventListener
    public void integerListener(Integer number) {
        logger.info(":: Received number: '" + number + "' ::");
    }
}
