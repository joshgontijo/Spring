package br.springscope.injection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestService {

	private static final Logger logger = LoggerFactory
			.getLogger(RequestService.class);

	public RequestService() {
		logger.info("############# " + getClass().getCanonicalName()
				+ " CREATED ###############");
	}

	public void doIt(String s) {
		
		s += "X";

		logger.info("**VALUE: " + s);

	}

}
