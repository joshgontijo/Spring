/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josue.liqui.liquibase.ejb;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Josue
 */
public class PersonTest {
    
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testX() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEST-PU");
        EntityManager em = emf.createEntityManager();
        
        String personUuid = UUID.randomUUID().toString();
        
        Person person = new Person();
        person.setUuid(personUuid);
        person.setPassword("psw");
        person.setUsername("username");
        
        em.persist(person);
        
        Person person2 = em.find(Person.class, personUuid);
        
        assertNotNull(person2);
        assertEquals(person, person2);
    }

    
    
}
