package com.bq.demo.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
 
public class JerseyResource extends ResourceConfig {
    public JerseyResource() {
    	register(RequestContextFilter.class);
        register(HelloWorldResource.class);
    }
}