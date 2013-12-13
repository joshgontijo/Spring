package br.spring.springchat.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import br.spring.springchat.controller.AsyncReceiver;
import br.spring.springchat.model.ChatMessage;
import br.spring.springchat.model.ChatUser;
import br.spring.springchat.storage.ChatRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepo;

	@Autowired
	private AsyncReceiver asyncReceiver;

	public DeferredResult<List<ChatMessage>> waitForMessageResponse(int userId) {

		final DeferredResult<List<ChatMessage>> messageCallbackWaiter = new DeferredResult<List<ChatMessage>>(100000L,
				Collections.emptyList());

		asyncReceiver.waitForMessage(messageCallbackWaiter, userId);

		messageCallbackWaiter.onCompletion(new Runnable() {
			@Override
			public void run() {
				// remove message to prevent duplicates
				chatRepo.removeMessages();
			}
		});

		return messageCallbackWaiter;
	}

	public void sendMessage(ChatMessage chatMessage) throws JMSException {

		String url = ActiveMQConnection.DEFAULT_BROKER_URL;
		String subject = "chat.master";

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic(subject);

		MessageProducer producer = session.createProducer(destination);

		ObjectMessage objectMessage = session.createObjectMessage(chatMessage);

		// Add property to be identified by the selector
		objectMessage.setLongProperty("ID", chatMessage.getRecipients().get(0).getId());
		producer.send(objectMessage);
		System.out.println("Sent message " + chatMessage);

		connection.close();
	}

}
