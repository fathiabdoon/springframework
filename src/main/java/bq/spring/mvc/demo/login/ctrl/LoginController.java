/*
Copyright (c) 2014 (Jonathan Q. Bo) 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package bq.spring.mvc.demo.login.ctrl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bq.spring.mvc.demo.login.domain.User;
import bq.spring.mvc.demo.login.service.UserService;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jan 23, 2014 8:49:03 AM
 *
 */

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String loginPage(){
		return "login/login_view";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView loginCheck(HttpServletRequest request, LoginInfoDto loginCommand){
		boolean canLogin = userService.canLogin(loginCommand.getUserName(), loginCommand.getUserPassword());
		
		if(!canLogin)
			return new ModelAndView("login/login_view", "error", "wrong username or password!");
		
		User user = userService.findUser(loginCommand.getUserName());
		user.setLastIP(request.getRemoteHost());
		user.setLastVisitDate(new Date());
		userService.loginSuccess(user);
		
		request.getSession().setAttribute("user", user);
		return new ModelAndView("login/login_result","user1",user);
	}
	
}
