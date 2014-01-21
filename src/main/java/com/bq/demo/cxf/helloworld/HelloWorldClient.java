package com.bq.demo.cxf.helloworld;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class HelloWorldClient {

	public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(IHelloworld.class);
        factory.setAddress("http://localhost:8080/demo_bq/ws/HelloWorld");
        
        IHelloworld service = (IHelloworld) factory.create();
        String msg = service.greeting("Mr Bo");
        System.out.println(msg);
    }
	
}
