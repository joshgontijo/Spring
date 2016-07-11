package com.josue;

import com.josue.interceptor.Logged;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Created by Josue on 11/07/2016.
 */
@Component
public class SampleControl {

    private static final Logger logger = Logger.getLogger(SampleControl.class.getName());

    @PostConstruct
    public void init() {
        logger.info(":: @PostConstruct ::");
    }

    @Logged("some dummy value")
    public String getMessage() {
        return "OK";
    }
}
