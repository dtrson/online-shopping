package com.sonduong.onlineshopping.model;

import java.io.Serializable;

import com.sonduong.shoppingbackend.dto.Address;
import com.sonduong.shoppingbackend.dto.User;

/**
 * @author Son Duong
 * 
 * 04.11.2017
 */
public class RegisterModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Address billing;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	
	

}
