package com.bq.demo.activemq.top.two;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;

public class PersistTopicTest {

	public static void main(String[] args){
		ExecutorService threadpool = Executors.newCachedThreadPool();
		for(int i = 0; i < 10; i++){
			threadpool.execute(new Thread("pub"+i){
				
				@Override
				public void run() {
					PersistTopicPublisher publisher = new PersistTopicPublisher(this.getName(), "ptopic1");
					try {
						publisher.start();
						
						for(int i = 0; i < 100; i++)
							publisher.sendMessage("message-" + i);
					} catch (JMSException e) {
						e.printStackTrace();
					} finally{
						try {
							publisher.close();
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}

		for(int i = 0; i < 10; i++){
			threadpool.execute(new Thread("thread"+i){
				@Override
				public void run() {
					PersistTopicSubscribe subscriber = new PersistTopicSubscribe(this.getName(), "ptopic1");
					try {
						subscriber.start();
						subscriber.receiveMessage();
					} catch (JMSException e) {
						e.printStackTrace();
					} finally{
//						try {
//							subscriber.close();
//						} catch (JMSException e) {
//							e.printStackTrace();
//						}
					}
				}
				
			});
		}
		
		
	}
	
}
