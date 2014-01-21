package com.bq.demo.multilang.ctrl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import com.bq.demo.json.ctrl.HelloWorld;

@Controller
@RequestMapping(value="/multilang")
public class MultiLangController {

	@Autowired
	private LocaleResolver localeResolver;
	
	@RequestMapping(value="/default")
	public String defaultAcceptedHeader(Model model){
		HelloWorld hello = new HelloWorld();
		hello.setName("Mr. BO");;
		model.addAttribute("hello", hello);
		return "multilang/default";
	}
	
	@RequestMapping(value="/url")
	public String urlparam(@RequestParam("mylocale") String mylocale,Model model, HttpServletRequest request, HttpServletResponse response){
		// change multilang by change locale dynamically
		localeResolver.setLocale(request, response, new Locale(mylocale));
		
		HelloWorld hello = new HelloWorld();
		hello.setName("Mr. BO");;
		model.addAttribute("hello", hello);
		return "multilang/default";
	}
	
}
