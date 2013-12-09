package br.activemq.sender.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.BlobMessage;
import org.apache.activemq.blob.BlobTransferPolicy;
import org.apache.activemq.blob.FileSystemBlobStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.activemq.sender.rest.HomeController;

@Service
public class SyncMessageSenderService {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean
	// that JMS server is on localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	private static final Logger logger = LoggerFactory.getLogger(SyncMessageSenderService.class);
	// Name of the queue we will be sending messages to
	private static String subject = "TESTQUEUE";

	public void sendMessage(String message) throws JMSException {

		// Getting JMS connection from the server and starting it
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// JMS messages are sent and received using a Session. We will
		// create here a non-transactional session object. If you want
		// to use transactions you should set the first parameter to 'true'
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Destination represents here our queue 'TESTQUEUE' on the
		// JMS server. You don't have to do anything special on the
		// server to create it, it will be created automatically.
		Destination destination = session.createQueue(subject);

		// MessageProducer is used for sending messages (as opposed
		// to MessageConsumer which is used for receiving them)
		MessageProducer producer = session.createProducer(destination);

		// We will send a small text message saying 'Hello' in Japanese
		TextMessage textMessage = session.createTextMessage(message);

		// Here we are sending the message!
		producer.send(textMessage);
		System.out.println("Sent message '" + textMessage.getText() + "'");

		connection.close();
	}

	public void saveFileSendMessage(InputStream is, File file) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);

			int read;
			byte[] bytes = new byte[1024];
			while ((read = is.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}

			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				// Not closing request streams

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		logger.info("SENDING FILE TO JMS QUEUE");
		ActiveMQConnection connection = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			connection = (ActiveMQConnection) connectionFactory.createConnection();
		
			connection.start();
			ActiveMQSession session = (ActiveMQSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue destination = session.createQueue("FILE_QUEUE");
			MessageProducer producer = session.createProducer(destination);
			BlobMessage message = session.createBlobMessage(new URL("http://localhost:8080/sender/getFile?fileName="+file.getName()));
			producer.send(message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		
	}
	
	

}
