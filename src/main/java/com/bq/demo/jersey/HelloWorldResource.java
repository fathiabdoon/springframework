package com.bq.demo.jersey;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("helloworld")
@Component
public class HelloWorldResource {

    private AtomicInteger counter = new AtomicInteger();
    
//    @Autowired
//    private IHelloWorldService service;

    @GET
    @Path("{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello(@PathParam("name") String name){
//        String greeting = service.greeting(name);
    	return String.format("%d: %s", counter.incrementAndGet(), "greeting");
    }
}
