package br.spring.springchat.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import br.spring.springchat.model.ChatMessage;
import br.spring.springchat.model.ChatUser;
import br.spring.springchat.service.ChatService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ChatController {

	/*
	 * THIS EXAMPLE SHOULD RUN ON SERVLET 3.0 AND <async-supported> SHOULD BE ENABLED
	 * JACKSON ALSO NEEDS TO BE ADDED TO PROJECT POM.XML (DeferredResult RETURN)
	 */
	
	@Autowired
	private ChatService chatService;

	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<List<ChatMessage>> waitForMessageResponse(@RequestParam int userId) {

		return chatService.waitForMessageResponse(userId);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public void postMessage(@RequestBody ChatMessage message) {

		logger.info("Message received: " + message);

		try {
			this.chatService.sendMessage(message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
