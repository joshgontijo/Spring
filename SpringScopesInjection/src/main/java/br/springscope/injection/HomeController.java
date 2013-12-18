package br.springscope.injection;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestScope;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	
	
	@Autowired
	private SingletonService singletonService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private RequestService requestService;
	
	private static String staticString = "v1";

	@RequestMapping(value = "/singleton", method = RequestMethod.GET)
	@ResponseBody
	public String singleton() {

		singletonService.doIt();
		return "OK...";
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	@ResponseBody
	public String session() {

		sessionService.doIt();
		return "OK...";
	}

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	@ResponseBody
	public String request() {

		requestService.doIt(staticString);
		return "OK...";
	}

}
