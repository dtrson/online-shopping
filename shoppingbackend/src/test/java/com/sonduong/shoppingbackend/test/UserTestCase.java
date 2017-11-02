package com.sonduong.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sonduong.shoppingbackend.dao.UserDAO;
import com.sonduong.shoppingbackend.dto.Address;
import com.sonduong.shoppingbackend.dto.Cart;
import com.sonduong.shoppingbackend.dto.User;

/**
 * @author Son Duong
 * 
 * 02.11.2017
 */
public class UserTestCase {
private static AnnotationConfigApplicationContext context;
	
	private static UserDAO userDAO;
	
	private User user = null;
	private Address address = null;
	private Cart cart = null;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sonduong.shoppingbackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	@Ignore
	@Test
	public void testAdd(){
		
		user = new User() ;
		user.setFirstName("Oc");
		user.setLastName("Buu");
		user.setEmail("ob@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		assertEquals("Failed to add user!", true, userDAO.addUser(user));
		
		//add billing address
		address = new Address();
		address.setAddressLineOne("P4 9");
		address.setAddressLineTwo("Near Cineplex Store");
		address.setCity("Mannheim");
		address.setState("BW");
		address.setCountry("Germany");
		address.setPostalCode("68161");
		address.setBilling(true);
		
		//link the user with this billing address
		address.setUserId(user.getId());
		assertEquals("Failed to add billing address!", true, userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")){
			
			//create new cart for this user
			cart = new Cart();
			cart.setUser(user);
			
//			assertEquals("Failed to add cart!", true, userDAO.addCart(cart));
			
			//add shipping address for this user
			address = new Address();
			address.setAddressLineOne("P4 9 S");
			address.setAddressLineTwo("Near Cineplex Store S");
			address.setCity("Mannheim S");
			address.setState("BW S");
			address.setCountry("Germany S");
			address.setPostalCode("68161 S");
			address.setShipping(true);
			
			//link this shipping address with the user
			address.setUserId(user.getId());
			assertEquals("Failed to add shipping address!", true, userDAO.addAddress(address));
		}
			
	}
	
	@Ignore
	@Test
	public void testAdd1(){
		
		user = new User() ;
		user.setFirstName("Oc");
		user.setLastName("Buu");
		user.setEmail("ob@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		
		
		
		
		if(user.getRole().equals("USER")){
			
			//create new cart for this user
			cart = new Cart();
			cart.setUser(user);
			
			//attach cart with the user
			user.setCart(cart);

		}
		assertEquals("Failed to add user!", true, userDAO.addUser(user));
			
	}
	
	@Test
	public void testUpdateCart(){
		
		//fetch the user by email
		user = userDAO.getByEmail("ob@gmail.com");
		
		//get the cart of the user
		cart = user.getCart();
		
		cart.setGrandTotal(5556);
		cart.setCartLines(2);
		assertEquals("Failed to update cart", true, userDAO.updateCart(cart));
	}
	
	
}
