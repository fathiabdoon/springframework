package com.bq.demo.json.ctrl;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bq.spring.mvc.domain.HelloWorld;

@Controller
@RequestMapping(value="/helloworld")
public class DemoHelloworldController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String testMainPage(){
		return "/helloworld/main";
	}

	@RequestMapping(value="/json",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody HelloWorld jsonhelloworld(@RequestBody HelloWorld helloworld){
		helloworld.setTimestamp(new Date());
		helloworld.setGreeting("hello json world!");
		return helloworld;
	}
	
	@RequestMapping(value="/xml",method=RequestMethod.POST)
	public @ResponseBody HelloWorld xmlhelloworld(@RequestBody HelloWorld helloworld){
		helloworld.setTimestamp(new Date());
		helloworld.setGreeting("hello xml world!");
		return helloworld;
	}
	
}
