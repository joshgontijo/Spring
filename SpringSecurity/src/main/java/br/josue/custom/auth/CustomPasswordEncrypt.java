package br.josue.custom.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class CustomPasswordEncrypt {
	private final static Logger logger = LoggerFactory.getLogger(CustomPasswordEncrypt.class);

	public boolean matches(String passwordPlain, String passwordHash) {

		logger.info("MATCHING PASSWORD: " + passwordPlain + " WITH: " + passwordHash);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//TODO remove test purpose -- and replace hashed with passwordHash
		String hashed = encode(passwordHash);
		
		return encoder.matches(passwordPlain, hashed);
	}

	public String encode(String passwordPlain) {
		
		logger.info("ENCODING PASSWORD: " + passwordPlain);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(passwordPlain);
	}

}
