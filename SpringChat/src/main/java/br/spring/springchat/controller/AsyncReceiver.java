package br.spring.springchat.controller;

import java.util.Arrays;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import br.spring.springchat.model.ChatMessage;
import br.spring.springchat.model.ChatUser;
import br.spring.springchat.service.ChatService;

@Component
// This proxy will locate the AsyncReceiver from the current request
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AsyncReceiver implements MessageListener {

	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Topic destinationTopic = null;
	private TopicSubscriber topicSubscriber = null;

	@Autowired
	ChatService chatService;

	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	private DeferredResult<List<ChatMessage>> messageCallbackWaiter;

	public AsyncReceiver() {

		logger.info("############ ASYNC RECEIVER CREATED...");
	}

	public void waitForMessage(DeferredResult<List<ChatMessage>> messageCallbackWaiter, int userId) {

		factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		try {
			connection = factory.createConnection();
			connection.setClientID("" + userId);
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// wildcard for all chat domains
			destinationTopic = session.createTopic("chat.*");
			// Selectors
			// http://docs.oracle.com/javaee/1.4/api/javax/jms/Message.html
			topicSubscriber = session.createDurableSubscriber(destinationTopic, "MY-TOPIC-SUB-NAME", "ID = " + userId,
					false);
			topicSubscriber.setMessageListener(this); // 1

			this.messageCallbackWaiter = messageCallbackWaiter;

			System.out.println("Waiting for message...");
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ChatMessage chatMessage;
			try {
				chatMessage = (ChatMessage) ((ObjectMessage) message).getObject();
				this.messageCallbackWaiter.setResult(Arrays.asList(chatMessage));
				logger.info("###### RECEIVED OBJECT MESSAGE: " + chatMessage);

			} catch (JMSException e1) {
				logger.error(e1.getMessage());
			} finally {
				try {
					topicSubscriber.close();
					session.close();
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

}
