package br.springjms.jms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.springjms.jms.message.Producer;

@Service
public class MessageService {

	@Autowired
	private Producer producer;

	public void sendMessage(String message) {
		producer.sendMessage(message);
	}

}
