package com.sonduong.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sonduong.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product) target;
		
		//whether file has been selected or not
		if(product.getFile()==null || product.getFile().getOriginalFilename().equals("")){
			errors.rejectValue("file", null,"Please select an image file to upload!");
			return;
		}
		
		//check the mine type
		if(! (product.getFile().getContentType().equals("image/jpeg") || 
				product.getFile().getContentType().equals("image/png") ||
						product.getFile().getContentType().equals("image/dif"))){
			errors.rejectValue("file", null, "Please use only image file for uploading!");
			return;
		}

	}

}
