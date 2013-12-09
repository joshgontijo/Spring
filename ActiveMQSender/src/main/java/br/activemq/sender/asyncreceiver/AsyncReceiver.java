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

public class AsyncReceiver implements MessageListener, Runnable {

	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;

	public AsyncReceiver() {

	}

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {

			try {
				System.out.println("STARTED TASK: " + ((TextMessage) message).getText());
				Thread.sleep(10000 + (int) (Math.random() * (20000 - 10000) + 1));
			} catch (InterruptedException e) {
			} // 2
			catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println("FINISHED TASK: " + ((TextMessage) message).getText());

			} catch (JMSException e) {
				System.err.println(e.getMessage());
			}

		}
	}

	@Override
	public void run() {
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

	// public static void main(String... args){
	// try {
	// new AsyncReceiver().receiveMessage();
	// } catch (JMSException ex) {
	// System.out.println(ex.getMessage());
	// }
	// }

}
