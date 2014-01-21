package com.bq.demo.json.ctrl;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement(name="HelloWorld")
public class HelloWorld {

	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date timestamp;
	
	private String greeting;
	
	public HelloWorld(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

}
