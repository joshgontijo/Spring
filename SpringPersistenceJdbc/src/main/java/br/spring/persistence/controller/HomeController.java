package br.spring.persistence.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.spring.persistence.controller.ex.MyCustomException;
import br.spring.persistence.i.IService;
import br.spring.persistence.model.User;
import br.spring.persistence.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private IService userService;

	public HomeController() {
		logger.info("******************* HomeController INITIALIZED *******************");
	}

	// Exception Handler
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exceptionHander(Exception exception, HttpServletResponse httpResponse) {

		if (exception instanceof MyCustomException) {
			httpResponse.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}
		return exception.getMessage();
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

	@RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = "application/json", produces = "text/plain")
	@ResponseBody
	public String createUser(@RequestBody User user) throws MyCustomException {

		userService.createUser(user);
		return "Persistence... SAVED !!!";

	}

}
