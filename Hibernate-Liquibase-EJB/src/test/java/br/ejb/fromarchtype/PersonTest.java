/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ejb.fromarchtype;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 
 * @author Josue
 */
public class PersonTest {

	private EntityManagerFactory emf;
	private EntityManager em;

	static final Logger logger = Logger.getLogger(PersonTest.class);

	public PersonTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		// setup basic config for Log4J
//		BasicConfigurator.configure();

		// Runs Liquibase from java code, useful when running Run As -> Junit
		// tests (without maven plugin)
		// try {
		// MysqlDataSource dataSource = new MysqlDataSource();
		// dataSource.setURL("jdbc:mysql://localhost:3306/ejb_db");
		// dataSource.setUser("root");
		// dataSource.setPassword("root");
		// Database database =
		// DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
		// new JdbcConnection(dataSource.getConnection()));
		// Liquibase liquibase = new
		// Liquibase("v-1.0/2013-09-21--01-initial-schema-import.xml", new
		// ClassLoaderResourceAccessor(), database);
		// liquibase.update(null);
		//
		//
		// } catch (DatabaseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (LiquibaseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		logger.info("INITIALIZING NEW TEST...");
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
			if (true) {
				throw new RuntimeException();
				// transaction.rollback();
				// System.out.println("ROLLED BACK #################");
			}
			transaction.commit();
			logger.info("SUCCESS #################");
		} catch (RuntimeException e) {
			logger.info("EXPECTED EXCEPTION ################# " + e);
			transaction.rollback();

		}
		// finally{
		// if(em.isOpen())
		// em.close();
		// }

		Person person2 = em.find(Person.class, personUuid);
		//
		assertNull(person2);
		// assertEquals(person, person2);
	}

}
