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

/**
 * <b> subscriber, show the basic usuage of Topic </b>
 *
 * <p> 
 * </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jan 22, 2014 11:47:31 PM
 *
 */
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
