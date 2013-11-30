package br.spring.persistence.data;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.spring.persistence.i.IData;
import br.spring.persistence.model.User;

@Transactional
@Repository
@Primary
public class HibernateData implements IData{
private static final Logger logger = LoggerFactory.getLogger(HibernateData.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public HibernateData(){
		logger.info("******************* HIBERNATE DATA INITIALIZED *******************");
	}
	
	@Override
	public void createUser(User user){
		logger.info("******************* HIBERNATE CREATE USER *******************");
		sessionFactory.getCurrentSession().save(user);
	}
}
