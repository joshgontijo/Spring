package br.spring.persistence.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.spring.persistence.i.IData;
import br.spring.persistence.i.IService;

@Service
public class UserService implements IService{

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	/*
	 *  Use @Qualifier for multiple implementations of the same interface
	 */
	@Autowired
	private IData persistence;

	public UserService(){
		logger.info("******************* USER SERVICE INITIALIZED *******************");
	}
	
	@Override
	public void createUser() {

		persistence.createUser();
	}

}
