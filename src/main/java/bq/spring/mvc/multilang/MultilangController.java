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
package bq.spring.mvc.multilang;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import com.bq.demo.json.ctrl.HelloWorld;

/**
 * <b> how to implement multi-lang </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 * 
 * Created at Jan 20, 2014 11:34:44 PM
 */

@Controller
@RequestMapping(value="/multilang")
public class MultilangController {

	@Autowired
	private LocaleResolver localeResolver;
	
	@RequestMapping(method=RequestMethod.GET)
	public String defaultAcceptedHeader(Model model){
		HelloWorld hello = new HelloWorld();
		hello.setName("Mr. BO");
		model.addAttribute("helloworld", hello);
		return "multilang/multilang_view";
	}
	
	@RequestMapping(value="/{locale}")
	public void changeLocale(@PathVariable("locale") String mylocale, HttpServletRequest request, HttpServletResponse response){
		// change multilang by change locale dynamically
		localeResolver.setLocale(request, response, new Locale(mylocale));
	}
	
}
