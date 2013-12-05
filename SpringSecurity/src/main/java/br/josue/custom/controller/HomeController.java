package br.josue.custom.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.josue.custom.auth.bean.MyUser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public HomeController() {
		logger.info("####### LOADED HOMECONTROLLER #######");
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "login";

	}
	
	@RequestMapping(value="/landing", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal ) {
 
//		String name = principal.getName();
//		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		return "logged-in/landing";
 
	}
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	/*
	 * CONSUMES / PRODUCES AVAILABLE FROM > SPRING 3.1.4
	 * http://docs.spring.io/spring
	 * /docs/3.1.4.RELEASE/spring-framework-reference/html/new-in-3.1.html
	 */

	@RequestMapping(value = "/secured", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String securedGet(HttpServletRequest request) {
		logger.info(request.getSession().getId());
		return "Congratz !!!";
	}

	/*
	 * Test for body json objects
	 */
	@RequestMapping(value = "/secured", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public MyUser createObject(@RequestBody MyUser myUser, HttpServletRequest request) {
		logger.info(request.getSession().getId());

		if (myUser == null) {
			throw new RuntimeException("MyUser object is null");
		}
		System.out.println(myUser);
		return myUser;
	}

}
