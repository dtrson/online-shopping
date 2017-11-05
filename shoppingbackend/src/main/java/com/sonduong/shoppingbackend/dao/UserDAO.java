package com.sonduong.shoppingbackend.dao;

import java.util.List;

import com.sonduong.shoppingbackend.dto.Address;
import com.sonduong.shoppingbackend.dto.Cart;
import com.sonduong.shoppingbackend.dto.User;

/**
 * @author Son Duong
 * 
 * 02.11.2017
 */
public interface UserDAO {

	public boolean addUser(User user);
	
	User getByEmail(String email);
	
	public boolean addAddress(Address address);
	
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	
	
}
