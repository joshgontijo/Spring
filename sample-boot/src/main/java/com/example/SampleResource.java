package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Josue on 11/07/2016.
 */
@RestController
public class SampleResource {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMongoRepository mongoRepository;

    @ResponseBody
    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public User getUser() {
        List<User> saved = mongoRepository.save(Arrays.asList(new User("yey"), new User("yey2")));
        return repository.save(new User( "Josh"));
    }
}
