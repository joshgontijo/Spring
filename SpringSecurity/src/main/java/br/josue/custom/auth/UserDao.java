package br.josue.custom.auth;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.josue.custom.auth.bean.MyUser;
import br.josue.custom.auth.bean.Role;

 
@Repository
public class UserDao {
 
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	public UserDao(){
		logger.info("######## CREATED UserDao ########");
	}
	
    public MyUser loadUserByUsername(final String username) {
        MyUser user = new MyUser();

        user.setUsername("USERNAME1-"+username);
        user.setPassword("PASSWORD1");
        Role r = new Role();
        r.setName("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(r);
        user.setAuthorities(roles);
        return user;
    }
}