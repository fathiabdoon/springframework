package com.bq.demo.jersey;

import java.util.Date;

public class HelloWorldService implements IHelloWorldService{

	@Override
	public String greeting(String username) {
		return String.format("Now is %tD, welcome %s", new Date(), username);
	}

	
}
