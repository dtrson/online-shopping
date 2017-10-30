package com.sonduong.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value= {"/", "/index","/home"})
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("page");
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
	public ModelAndView showAllProducts(){
		
		ModelAndView mv = new ModelAndView("page");
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
	public ModelAndView showSingleProduct(@PathVariable int id){
		
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		//update view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
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
