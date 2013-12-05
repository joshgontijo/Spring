package br.activemq.async.desktopapp.receiver;

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

public class AsyncReceiver implements MessageListener {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public void receiveMessage() throws JMSException {

        factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("TESTQUEUE");
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(this); // 1
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        } // 2

    }

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                System.out.println("Async message received: " + ((TextMessage) message).getText());
            } catch (JMSException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
