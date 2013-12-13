package br.josue.devtech;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.Content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/getMessage", method = RequestMethod.GET)
	@ResponseBody
	public Message getMessage() {

		return new Message();
	}

	@RequestMapping(value = "/getMessageById", method = RequestMethod.GET)
	public @ResponseBody Message getMessageById(@RequestParam("messageId") int messageId) {

		if (messageId == 1) {
			return new Message();
		}
		return null;
	}
	
	@RequestMapping(value = "/saveMessage", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Message saveMessage(@RequestBody Message message){
		if(message != null){
			logger.info(message.toString());
			return message;

		}
		return null;
	}
	
	
	
	//SIMPLE CONTROLLER
	@RequestMapping(value = "/bootstrap", method = RequestMethod.GET)
	public String index() {

		return "bootstrap";
	}
}
