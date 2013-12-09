package br.activemq.sync.desktopapp.receiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.BlobMessage;

public class SyncFileMessageReceiver {
	
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;

	public void receiveMessage() throws IOException {
		try {
			factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FILE_QUEUE");
			consumer = session.createConsumer(destination);

			// Blocking
			System.out.println("Waiting for message on QUEUE: OBJECTQUEUE...");
			Message message = consumer.receive();

			if (message instanceof BlobMessage) {
				BlobMessage receivedBlobMessage = (BlobMessage) message;
				System.out.println("Object Message is : " +receivedBlobMessage);
				
				 InputStream in = receivedBlobMessage.getInputStream();
				 OutputStream os = new FileOutputStream(new File("receivedFile.zip"));
				 
				 byte[] buffer = new byte[1024];  
				 int bytesRead;  
				 while ((bytesRead = in.read(buffer)) != -1) {  
				   os.write(buffer, 0, bytesRead);  
				 }  
				 in.close();  
				 os.close();  
				 
			}

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		SyncFileMessageReceiver receiver = new SyncFileMessageReceiver();
		receiver.receiveMessage();
		System.exit(0);
	}
}
