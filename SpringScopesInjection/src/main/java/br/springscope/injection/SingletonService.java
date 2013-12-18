package br.springscope.injection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SingletonService {

	private static final Logger logger = LoggerFactory
			.getLogger(SingletonService.class);

	public SingletonService() {
		logger.info("############# " + getClass().getCanonicalName()
				+ " CREATED ###############");
	}

	public void doIt() {

	}
}
