package br.springscope.injection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionService {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

	public SessionService(){
		logger.info("############# "+getClass().getCanonicalName() + " CREATED ###############");
	}
	
	
	public void doIt() {

	}
}
