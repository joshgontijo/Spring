package com.josue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Josue on 11/07/2016.
 */
@RestController
public class SampleResource {

    @Autowired
    private SampleControl control;

    @RequestMapping(path = "/sample")
    public String start() {
        return control.getMessage();
    }
}
