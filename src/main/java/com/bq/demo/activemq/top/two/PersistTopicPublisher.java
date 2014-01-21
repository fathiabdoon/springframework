package com.bq.demo.activemq.top.two;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class PersistTopicPublisher {

	private Connection connection = null;
	private Session session = null;
	private MessageProducer publisher;
	private String name = null;
	private String topicName = null;
	
	public PersistTopicPublisher(String name, String topicName){
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		try {
			connection = connectionFactory.createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		this.name = name;
		this.topicName = topicName;
	}
	
	public void start() throws JMSException{
		session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic(topicName);
		publisher = session.createProducer(topic);
		publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		connection.start();
	}
	
	public void sendMessage(String strmessage) throws JMSException{
		TextMessage message = session.createTextMessage(name + "-" + strmessage);
		publisher.send(message);
		System.out.println("**" + name + "send:" + message);
		
		session.commit();
	}
	
	public void close() throws JMSException{
		connection.close();
	}	

}
