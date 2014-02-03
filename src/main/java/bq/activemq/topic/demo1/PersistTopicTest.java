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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;

/**
 * <b>  </b>
 *
 * <p> 
 * </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jan 22, 2014 11:52:17 PM
 *
 */
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
