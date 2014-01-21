package com.bq.demo.cxf.helloworld;

import javax.jws.WebService;

@WebService
public interface IHelloworld {

	public String greeting(String userName);
	
}
