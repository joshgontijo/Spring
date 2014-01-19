package br.arq.jb.managed.arquillian.jboss.managed.dao;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.arq.jb.managed.arquillian.jboss.managed.User;

@RunWith(Arquillian.class)
public class UserFacadeTest {

	@EJB
	private UserFacade userFacade;
	
    @Deployment
    public static Archive<?> createDeployment() {
    	    	
        // You can use war packaging...
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(User.class.getPackage())
                .addPackage(UserFacade.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        // or jar packaging...
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(User.class.getPackage())
                .addPackage(UserFacade.class.getPackage())
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsManifestResource("jbossas-ds.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        // choose your packaging here
        return jar;
    }

    @Test
    public void testA() {
    	User user = new User();
    	user.setId(123L);
    	userFacade.create(user);
    	
    	User foundUser = userFacade.find(user.getId());
    	assertEquals(user, foundUser);
    	
    	System.out.println("################## FINISHED");
        
    }
}
