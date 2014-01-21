package com.bq.demo.activemq.queue.one;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class Producer {

	public static void main(String[] args){
		Connection connection = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Queue destination = session.createQueue("queue1");
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			// produce message
			for (int i = 1; i <= 100; i++) {
	            TextMessage message = session.createTextMessage("message:" + i);  
	            producer.send(message);  
	        }  
			
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally{
			try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
