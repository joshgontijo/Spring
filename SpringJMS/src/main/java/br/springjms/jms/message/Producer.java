package br.springjms.jms.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	private static Logger logger = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(String message) {
		jmsTemplate.convertAndSend(message);
		logger.info("Sending message: " + message);
	}

}
