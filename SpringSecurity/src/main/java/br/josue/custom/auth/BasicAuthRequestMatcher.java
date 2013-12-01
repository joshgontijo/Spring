package br.josue.custom.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Component;

/*
 *This class is used to check if the request contains Authorization header,
 *but can be used for any kind o check for URL pattern o other
 */
@Component
public class BasicAuthRequestMatcher implements RequestMatcher {

	private final static Logger logger = LoggerFactory.getLogger(BasicAuthRequestMatcher.class);

	@Override
	public boolean matches(HttpServletRequest request) {
		boolean hasHeader = StringUtils.isNotBlank(request.getHeader("Authorization"));
		if (hasHeader) {
			logger.info("##### REST REQUEST BASED #####");
		}

		return hasHeader;
	}

}
