package br.activemq.sender.asyncreceiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringWrappedRunnablesReceivers implements MessageListener {

	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;

	public SpringWrappedRunnablesReceivers() {
		factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		try {
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("TESTQUEUE");
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(this); // 1
			System.out.println("Waiting for message...");
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("RECEIVED MESSAGE: " + ((TextMessage) message).getText());
			Thread.sleep(10000 + (int) (Math.random() * (20000 - 10000) + 1));
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		} // 2
		catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("FINISHED MESSAGE: " + ((TextMessage) message).getText());

		} catch (JMSException e) {
			System.err.println(e.getMessage());
		}

	}

}
