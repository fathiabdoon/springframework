package com.bq.demo.activemq.top.two;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

public class PersistTopicSubscribe {
	private Connection connection = null;
	private Session session = null;
	private MessageConsumer subscriber;
	private String name;
	private String topicName = null;
	
	public PersistTopicSubscribe(String name, String topicName){
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
		// must set unique clientID, or occur exception
		connection.setClientID(name);
		session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic(topicName);
		subscriber = session.createDurableSubscriber(topic, name);
		
		
		connection.start();
	}
	
	public void receiveMessage() throws JMSException{
		subscriber.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage txtmessage = (TextMessage) message;
				try {
					System.out.println(name + ": consume " + txtmessage.getText());
					session.commit();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void close() throws JMSException{
		connection.close();
	}	

}
