package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {
	
@RequestMapping(value="welcome.html")
public ModelAndView homePage(){
 return new ModelAndView("index");	
	
}	

}
