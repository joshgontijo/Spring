package br.springjms.test.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(Producer.class);

	@Override
	public void onMessage(Message message) {
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
