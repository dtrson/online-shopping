package com.sonduong.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Son Duong
 * 
 * 29.10.2017
 */

@Controller
public class PageController {
	
	@RequestMapping(value= {"/", "/index","/home"})
	public ModelAndView index(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting","Welcome to Spring WebMVC");
		return mv;
	}
	
	/** RequestParam: url?key=value --> works with url query
	
	@RequestMapping(value="/test")
	public ModelAndView test(@RequestParam(value="greeting", required=false) String greeting){
		if(greeting== null){
			greeting = "Son";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;
	} 
	
	**/
	
	/**PathVariable: url/value   **/
	@RequestMapping(value="/test/{greeting}")
	public ModelAndView test(@PathVariable("greeting") String greeting){
		if(greeting== null){
			greeting = "SonDD";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;
	}


}
