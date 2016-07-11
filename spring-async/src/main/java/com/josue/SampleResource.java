package com.josue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Josue on 11/07/2016.
 */
@RestController
public class SampleResource {

    @Autowired
    private AsyncControl control;

    @RequestMapping(path = "/start")
    public String start(@RequestParam(value = "threads", defaultValue = "1") Integer threads) {
        for (int i = 0; i < threads; i++) {
            control.executeAsync();
        }
        return "Started";
    }
}
