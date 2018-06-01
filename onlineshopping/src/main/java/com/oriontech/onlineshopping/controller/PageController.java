package com.oriontech.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting","Welcome to Spring MVC!");
		return mv;
	}
	//RequesParam kullanýlmasý
	@RequestMapping(value="/test")
	public ModelAndView test(@RequestParam("greeting")String greeting) {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;
	}
	//Parametre verilmediðinde hatanýn giderilmesi
	@RequestMapping(value="/test2")
	public ModelAndView test2(@RequestParam(value="greeting",required=false)String greeting) {
		if(greeting==null) {
			greeting="Hello there";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;		
	}
	//PathVarible kullanýlmasý
	@RequestMapping(value="/test3/{greeting}")
	public ModelAndView test3(@PathVariable("greeting")String greeting) {
		if(greeting==null) {
			greeting="Hello there";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;		
	}
}

