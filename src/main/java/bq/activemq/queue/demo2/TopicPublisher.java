package bq.activemq.queue.demo2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.spring.ActiveMQConnectionFactory;

/**
 * non persist message, so mush first run subscriber, then run publish, or subscribe cannot receive any message at all!
 * @author boqi
 *
 */
public class TopicPublisher {

	public static void main(String[] args){
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("topic1");
			MessageProducer publisher = session.createProducer(topic);
			
			for(int i = 0; i < 100; i++){
				TextMessage message = session.createTextMessage("message "+i);
				publisher.send(message);
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
