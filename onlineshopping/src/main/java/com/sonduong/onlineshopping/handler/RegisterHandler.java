package com.sonduong.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.sonduong.onlineshopping.model.RegisterModel;
import com.sonduong.shoppingbackend.dao.UserDAO;
import com.sonduong.shoppingbackend.dto.Address;
import com.sonduong.shoppingbackend.dto.Cart;
import com.sonduong.shoppingbackend.dto.User;

/**
 * @author Son Duong
 * 
 * 04.11.2017
 */

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init(){
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user){
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing){
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model){
		
		String transitionValue = "success";
		
		//fetch user
		User user = model.getUser();
		
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//save user
		userDAO.addUser(user);
		
		
		//get the address
		Address billing = model.getBilling();
		
		billing.setUser(user);
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);

		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext error){
		
		String transitionValue="success";
		
		//checking if password matches confirm password
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			
			error.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password does not match the confirm password!")
					.build());
			
			transitionValue = "failure";
		}
		
		//check uniqueness email
		if(userDAO.getByEmail(user.getEmail()) != null){
			
			error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("Email address is already used!")
					.build());
			transitionValue = "failure";
		}
		
		return transitionValue;
	}
}
