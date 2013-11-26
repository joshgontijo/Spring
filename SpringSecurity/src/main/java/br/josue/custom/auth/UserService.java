package br.josue.custom.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.josue.custom.auth.bean.MyUser;
 

 
@Service
public class UserService implements UserDetailsService {
 
	@Autowired
    private UserDao userDao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	public UserService(){
		logger.info("######## CREATED UserService ########");
	}


	@Override
    public MyUser loadUserByUsername(final String username) throws UsernameNotFoundException {
		return userDao.loadUserByUsername(username);
        
    }
    
    
}