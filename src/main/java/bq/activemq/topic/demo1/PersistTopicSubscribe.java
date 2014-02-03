/*
Copyright (c) 2014 (Jonathan Q. Bo) 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package bq.activemq.topic.demo1;

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

/**
 * <b> show the usuage of persistence Topic </b>
 *
 * <p> 
 * </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jan 22, 2014 11:49:49 PM
 *
 */
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
