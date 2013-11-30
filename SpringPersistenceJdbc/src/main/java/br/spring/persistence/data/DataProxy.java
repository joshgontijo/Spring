package br.spring.persistence.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.spring.persistence.i.IData;
import br.spring.persistence.i.IProxy;
import br.spring.persistence.model.User;

@Repository
@Primary
public class DataProxy implements IProxy {

	private static final Logger logger = LoggerFactory.getLogger(JdbcData.class);

	public static enum DataAccessType {
		JDBC, HIBERNATE
	}

	
	
	private IData dataRef;

	@Autowired
	public DataProxy(IData persistenceTarget) {
		logger.info("******************* DATA PROXY INITIALIZED *******************");
		this.dataRef = persistenceTarget;
	}

	@Override
	public void createUser(User user) {
		dataRef.createUser(user);

	}

	@Override
	public void setPersistenceDataAccess(IData data) {
		this.dataRef = data;

	}

}
