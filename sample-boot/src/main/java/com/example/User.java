package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by Josue on 11/07/2016.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    @PrePersist
    public void init(){
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
