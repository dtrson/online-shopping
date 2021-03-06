package com.sonduong.onlineshopping.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sonduong.onlineshopping.exception.ProductNotFoundException;
import com.sonduong.shoppingbackend.dao.CategoryDAO;
import com.sonduong.shoppingbackend.dao.ProductDAO;
import com.sonduong.shoppingbackend.dto.Category;
import com.sonduong.shoppingbackend.dto.Product;

/**
 * @author Son Duong
 * 
 * 29.10.2017
 */

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value= {"/", "/index","/home"})
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("page");
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		mv.addObject("title","Home");
		mv.addObject("categories",categoryDAO.listActiveCategories());
		mv.addObject("userClickHome", true);
		return mv;
	}
	
	@RequestMapping(value= "/about")
	public ModelAndView about(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value= "/contact")
	public ModelAndView contact(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	/**Load all products based on category**/
	@RequestMapping(value= "/show/all/products")
	public ModelAndView showAllProducts(@RequestParam(value="result", required=false)String result){
		
		ModelAndView mv = new ModelAndView("page");
		
		if(result!=null){
			mv.addObject("message", "This choosen product is not available anymore!");
		}
		
		mv.addObject("title","All Products");
		mv.addObject("categories",categoryDAO.listActiveCategories());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	
	/**Load all products based on category**/
	@RequestMapping(value= "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id){
		
		ModelAndView mv = new ModelAndView("page");
		
		//categoryDAO to fetch a single category
		Category category = null;
		category = categoryDAO.get(id);
		
		mv.addObject("title",category.getName());
		mv.addObject("categories",categoryDAO.listActiveCategories());
		mv.addObject("category",category);
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	/**Viewing single product**/
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		//update view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		return mv;
		
	}
	
	/*having similar mapping to flow id, 
	but because the order of webflow (in dispatcher-servlet) is set to -1: has priority 
	-> the flow will be triggered and this request mapping is not called*/
	@RequestMapping(value= "/register")
	public ModelAndView register(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About Us");
		return mv;
	}
	
	/* Spring Login*/
	@RequestMapping(value= "/login")
	public ModelAndView login(@RequestParam(name="error", required=false)String error, @RequestParam(name="logout", required=false)String logout){
		
		ModelAndView mv = new ModelAndView("login");
		
		if(error != null){
			mv.addObject("message","Invalid credential!");
		}
		
		if(logout != null){
			mv.addObject("logout","Successfully logged out!");
		}
		mv.addObject("title","Login");
		return mv;
	}
	
	/*access denied page*/
	@RequestMapping(value= "/access-denied")
	public ModelAndView accessDenied(){
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title"," 403 - Access Denied");
		mv.addObject("errorTitle","Aha! Caught You!");
		mv.addObject("errorDescription","You are not authorized to view this page!");
		return mv;
	}
	
	/*for logout*/
	@RequestMapping(value="/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		
		//fetch authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
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
	
	/**PathVariable: url/value   
	@RequestMapping(value="/test/{greeting}")
	public ModelAndView test(@PathVariable("greeting") String greeting){
		if(greeting== null){
			greeting = "SonDD";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;
	}
	**/

}
