/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.activemq.sync.desktopapp.receiver;

import br.activemq.sender.mod.User;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author Josue
 */
public class SyncObjectMessageReceiver {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;


    public void receiveMessage() {
        try {
            factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("OBJECTQUEUE");
            consumer = session.createConsumer(destination);

            //Blocking
            System.out.println("Waiting for message on QUEUE: OBJECTQUEUE...");
            Message message = consumer.receive();

            if (message instanceof ObjectMessage) {
                ObjectMessage receivedObjectMessage = (ObjectMessage) message;
                System.out.println("Object Message is : " + (User) receivedObjectMessage.getObject());
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SyncObjectMessageReceiver receiver = new SyncObjectMessageReceiver();
        receiver.receiveMessage();
        System.exit(0);
    }
}
