package br.spring.persistence.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.spring.persistence.i.IData;

@Repository
@Primary
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class JdbcData implements IData{

	private static final Logger logger = LoggerFactory.getLogger(JdbcData.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcData(){
		logger.info("******************* JDBC DATA INITIALIZED *******************");
	}
	
	@Override
	public void createUser(){
		logger.info("******************* JDBC DATA CREATE USER *******************");
	}
}
