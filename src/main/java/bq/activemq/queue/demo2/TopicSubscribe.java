package bq.activemq.queue.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

public class TopicSubscribe {

	public static void main(String[] args){
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			final Connection connection = connectionFactory.createConnection();
			connection.start();
			
			ExecutorService threadpool = Executors.newFixedThreadPool(10);
			for(int i = 0; i < 10; i++){
				threadpool.execute(new Runnable() {
					
					@Override
					public void run() {
						try{
							final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
							Topic topic = session.createTopic("topic1");
							
							MessageConsumer subscriber = session.createConsumer(topic);
							subscriber.setMessageListener(new MessageListener() {
								
								@Override
								public void onMessage(Message message) {
									try {
										TextMessage txtmessage = (TextMessage) message;
										System.out.println(Thread.currentThread().getId() + ": consume " + txtmessage.getText());
										// let's it work
										session.commit();
									} catch (JMSException e) {
										e.printStackTrace();
									}
								}
							});
							
						}catch(JMSException e){
							e.printStackTrace();
						}						
					}
				});
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
