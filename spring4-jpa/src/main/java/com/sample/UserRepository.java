package com.sample;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Josue on 11/07/2016.
 */
public interface UserRepository extends JpaRepository<User, String> {

}