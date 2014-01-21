package com.bq.demo.cxf.helloworld;

import javax.jws.WebService;

@WebService(endpointInterface="com.bq.demo.cxf.helloworld.IHelloworld")
public class HelloworldImp implements IHelloworld{

	@Override
	public String greeting(String userName) {
		return "hello " + userName;
	}

}
