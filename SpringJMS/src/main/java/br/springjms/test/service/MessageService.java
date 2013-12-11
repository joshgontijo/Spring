package br.springjms.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.springjms.test.message.Producer;

@Service
public class MessageService {

	@Autowired
	private Producer producer;

	public void sendMessage(String message) {
		producer.sendMessage(message);
	}

}
