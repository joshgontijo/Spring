package br.josue.custom.auth;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.josue.custom.auth.bean.MyUser;

@Component
public class CustomFormAuthenticationProvider implements AuthenticationProvider {
	 
		private static final Logger logger = LoggerFactory.getLogger(CustomFormAuthenticationProvider.class);
		public CustomFormAuthenticationProvider(){
			logger.info("######## CREATED CustomFormAuthenticationProvider ########");
		}
		
		
	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private CustomPasswordEncrypt hasher;
	 
	    @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	        String username = authentication.getName();
	        String password = (String) authentication.getCredentials();
	 
	        MyUser user = userService.loadUserByUsername(username);
	 
	        if (user == null) {
	            throw new BadCredentialsException("Username not found.");
	        }
	 
	        //params: provided - database data
	        if (!hasher.matches(password, user.getPassword())) {
	            throw new BadCredentialsException("Wrong password.");
	        }
	 
	        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	 
	        logger.info("****** AUTHENTICATED USER *****");
	        return new UsernamePasswordAuthenticationToken(username, password, authorities);
	    }
	 
	    @Override
	    public boolean supports(Class<?> arg0) {
	        return true;
	    }
}
