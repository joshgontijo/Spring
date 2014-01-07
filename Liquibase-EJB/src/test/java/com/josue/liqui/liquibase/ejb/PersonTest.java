/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.liqui.liquibase.ejb;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Josue
 */
public class PersonTest {

    EntityManagerFactory emf;
    EntityManager em;

    private final static Logger logger = LoggerFactory.getLogger(PersonTest.class);
    
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
        emf = Persistence.createEntityManagerFactory("TEST-MYSQL-PU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testSimple() {

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

    @Test
    public void testTransaction() {

        EntityTransaction transaction = em.getTransaction();

        String personUuid = UUID.randomUUID().toString();

        Person person = new Person();
        person.setUuid(personUuid);
        person.setPassword("psw");
        person.setUsername("username");

        transaction.begin();
        em.persist(person);
        transaction.commit();

        Person person2 = em.find(Person.class, personUuid);

        assertNotNull(person2);
        assertEquals(person, person2);
    }

    @Test
    public void testTransactionFail() {

        EntityTransaction transaction = em.getTransaction();

        String personUuid = UUID.randomUUID().toString();

        Person person = new Person();
        person.setUuid(personUuid);
        person.setPassword("");
        person.setUsername("username2");

        try {
            transaction.begin();
            em.persist(person);
            if(true){
                throw new RuntimeException();
//                transaction.rollback();
//                System.out.println("ROLLED BACK #################");
            }
            transaction.commit();
            System.out.println("SUCCESS #################");
        } catch (RuntimeException e) {
            System.out.println("EXCEPTION ################# "+ e);
            transaction.rollback();

        }
//        finally{
//            if(em.isOpen())
//                em.close();
//        }
        
        Person person2 = em.find(Person.class, personUuid);
//
        assertNull(person2);
//        assertEquals(person, person2);
    }

}
