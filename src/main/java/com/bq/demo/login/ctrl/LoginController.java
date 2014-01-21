package com.bq.demo.login.ctrl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bq.demo.login.domain.User;
import com.bq.demo.login.service.UserService;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index.html")
	public String loginPage(){
		return "login/login";
	}
	
	@RequestMapping(value="/logincheck.html")
	public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand){
		boolean canLogin = userService.canLogin(loginCommand.getUserName(), loginCommand.getUserPassword());
		
		if(!canLogin)
			return new ModelAndView("login/login", "error", "wrong username or password!");
		
		User user = userService.findUser(loginCommand.getUserName());
		user.setLastIP(request.getRemoteHost());
		user.setLastVisitDate(new Date());
		userService.loginSuccess(user);
		
		request.getSession().setAttribute("user", user);
		return new ModelAndView("login/main");
	}
	
	
	
}
