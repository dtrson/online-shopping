package com.sonduong.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sonduong.onlineshopping.util.FileUploadUtility;
import com.sonduong.onlineshopping.validator.ProductValidator;
import com.sonduong.shoppingbackend.dao.CategoryDAO;
import com.sonduong.shoppingbackend.dao.ProductDAO;
import com.sonduong.shoppingbackend.dto.Category;
import com.sonduong.shoppingbackend.dto.Product;

/**
 * @author Son Duong
 * 
 * 31.10.2017
 */
@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product nProduct = new Product();
		
		//set few of the fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product",nProduct);
		
		if(operation!=null){
			if(operation.equals("product")){
				mv.addObject("message","Product Submitted Successfully!");
			}
			else if(operation.equals("category")){
				mv.addObject("message","Category Submitted Successfully!");
			}
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request){
		
		//custom product validator
		//case add new product
		if(mProduct.getId()==0){
			new ProductValidator().validate(mProduct, results);
		}
		//case update product
		else{
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		
		//check if there are any errors
		if(results.hasErrors()){
			
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message", "Vadidation failed for product's Submittion!");
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		//check if the request is add or edit/update
		if(mProduct.getId()==0){
			//create new product record
			productDAO.add(mProduct);
		}else{
			//update the product
			productDAO.update(mProduct);
		}
		
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		
		//fetch product for this ID
		Product product = productDAO.get(id);
		boolean isActive =  product.isActive();
		
		//deactivate this product
		product.setActive(!isActive);
		
		productDAO.update(product);
		
		return (isActive) ? "You have successfully deactivated the product with id " + product.getId() : "You have successfully activated the product with id " + product.getId();
	}
	
	//to handle category submission
	@RequestMapping(value="/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		//fetch product from database
		Product nProduct = productDAO.get(id);
		
		mv.addObject("product",nProduct);
		
		return mv;
		
	}
	
	//returning categories for the request mapping 
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	//returning category for the request mapping 
		@ModelAttribute("category")
		public Category getCategory(){
			return new Category();
		}
}
