package com.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Josue Gontijo <>.
 */
@Transactional
@Repository
@RestController
public class SampleResource {

    @Autowired
    private JpaRepository repository;

    @ResponseBody
    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public User hello() {
       return (User) repository.save(new User(27, "josh"));
    }

}
