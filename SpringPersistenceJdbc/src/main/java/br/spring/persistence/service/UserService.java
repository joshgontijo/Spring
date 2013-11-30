package br.spring.persistence.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.spring.persistence.controller.ex.MyCustomException;
import br.spring.persistence.i.IProxy;
import br.spring.persistence.i.IService;
import br.spring.persistence.model.User;

@Service
@Transactional
public class UserService implements IService{

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	/*
	 *  Use @Qualifier for multiple implementations of the same interface
	 */
	@Autowired
	private IProxy persistenceProxy;

	public UserService(){
		logger.info("******************* USER SERVICE INITIALIZED *******************");
	}
	
	@Override
	public void createUser(User user) throws MyCustomException {

		if(user.getUsername().equals("EXCEPTION")){
			throw new MyCustomException("MY CUSTOM EXCEPTION");
		}
		persistenceProxy.createUser(user);
	}

}
