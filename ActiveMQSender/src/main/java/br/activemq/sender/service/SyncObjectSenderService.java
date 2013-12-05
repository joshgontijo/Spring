package br.activemq.sender.service;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.activemq.sender.mod.User;
import br.activemq.sender.rest.HomeController;


@Service
public class SyncObjectSenderService  {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public void sendMessage() throws JMSException {


		try {
			
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			//Create Connection using the factory
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Create Queue
            Destination destQueue = session.createQueue("OBJECTQUEUE");
            //Create Producer
            MessageProducer producer = session.createProducer(destQueue);

            ObjectMessage objectMessage = session.createObjectMessage();
			User user = new User("Josue", 23);

			objectMessage.setObject(user);
			connection.start();
			producer.send(objectMessage);
			System.out.println(this.getClass().getName() + "has sent a message : " + user);

			producer.close();
			session.close();
			connection.close();
			
		} catch (JMSException e) {
			System.out.println("Problem with ActiveMQ");
			e.printStackTrace();
		}

	}

}
