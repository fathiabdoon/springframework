package com.bq.demo.theme.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ThemeResolver;

@Controller("themes")
@RequestMapping(value="/theme")
public class DynamicThemeController {
	
	@Autowired
	private ThemeResolver themeResolver;

	@RequestMapping(value="/default")
	public String defaultTheme(Model model){
		return "themes/main";
	}
	
	@RequestMapping(value="/{theme}")
	public void changeTheme(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		themeResolver.setThemeName(request, response, theme);
	}

	public ThemeResolver getThemeResolver() {
		return themeResolver;
	}

	public void setThemeResolver(ThemeResolver themeResolver) {
		this.themeResolver = themeResolver;
	}
	
}
