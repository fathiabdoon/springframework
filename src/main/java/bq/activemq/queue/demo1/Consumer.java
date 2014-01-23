package bq.activemq.queue.demo1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class Consumer {

	public static void main(String[] args){
		Connection connection = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			connection = connectionFactory.createConnection();
			connection.start();
			final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Queue destination = session.createQueue("queue1");
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					try {
						TextMessage textMessage=(TextMessage)message;
						System.out.println(textMessage.getText());
						
						session.commit();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}
	
}
