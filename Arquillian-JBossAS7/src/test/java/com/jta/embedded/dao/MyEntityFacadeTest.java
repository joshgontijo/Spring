/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jta.embedded.dao;

import java.util.UUID;

import com.jta.embedded.entity.MyEntity;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.junit.runner.RunWith;

/**
 * 
 * @author Josue
 */
@RunWith(Arquillian.class)
public class MyEntityFacadeTest {

	private static final Logger logger = Logger.getLogger(MyEntityFacadeTest.class);
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction utx;

	@EJB
	private MyEntityFacade clienteServices;

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "testCliente.jar").addPackages(true, "com.jta.embedded")
				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
				.addAsManifestResource("persistence.xml", "persistence.xml");
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

	/**
	 * Test of create method, of class MyEntityFacade.
	 */
	@Test
	public void testCreate() throws Exception {

		utx.begin();
		logger.info("TRNSACTION STARTED...");
		em.joinTransaction();
		System.out.println("Dumping old records...");
		em.persist(new MyEntity(UUID.randomUUID().toString(), "Josue", "asasa22s"));
		utx.commit();
		logger.info("TRNSACTION FINISHED...");
	}

}
