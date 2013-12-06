package br.activemq.sender.rest;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.activemq.sender.service.AsyncMessageSenderService;
import br.activemq.sender.service.SyncMessageSenderService;
import br.activemq.sender.service.SyncObjectSenderService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	SyncMessageSenderService syncMessageSenderService;
	
	@Autowired
	SyncObjectSenderService syncObjectSenderService;
	
	@Autowired
	AsyncMessageSenderService asyncMessageSenderService;
	
	@ExceptionHandler
	public String exceptionHandler(Exception ex){
		return ex.getMessage();
		
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

	@RequestMapping(value = "/syncMessage", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String syncMessage(@RequestParam String message) throws JMSException {
		logger.info("Sending message: " + message);

		syncMessageSenderService.sendMessage(message);
		
		return "Simple Sync message sent !!!";
	}
	
	@RequestMapping(value = "/objectMessage", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String objectMessage() throws JMSException {
		logger.info("Sending Object message...");

		syncObjectSenderService.sendMessage();
		
		return "Object message sent !!!";
	}

}
