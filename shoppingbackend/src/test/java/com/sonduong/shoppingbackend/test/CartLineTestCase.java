package com.sonduong.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sonduong.shoppingbackend.dao.CartLineDAO;
import com.sonduong.shoppingbackend.dao.ProductDAO;
import com.sonduong.shoppingbackend.dao.UserDAO;
import com.sonduong.shoppingbackend.dto.Cart;
import com.sonduong.shoppingbackend.dto.CartLine;
import com.sonduong.shoppingbackend.dto.Product;
import com.sonduong.shoppingbackend.dto.User;

/**
 * @author Son Duong
 * 
 *         05.11.2017
 */
public class CartLineTestCase {
	private static AnnotationConfigApplicationContext context;

	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;

	private CartLine cartLine = null;
	private Product product = null;
	private User user = null;
	private Cart cart = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sonduong.shoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
		productDAO = (ProductDAO) context.getBean("productDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	

	@Test
	public void testAddNewCartLine(){
		
		//get user
		user = userDAO.getByEmail("dtrson@live.de");
		
		//fetch the cart
		cart = user.getCart();
		
		// get products
		product = productDAO.get(1);
		
		//create new cartline
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrice());
		
		cartLine.setProductCount(cartLine.getProductCount()+1);
		
		cartLine.setTotal(cartLine.getProductCount()*product.getUnitPrice());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the cartLine", true, cartLineDAO.add(cartLine));
		
		//update the cart 
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		assertEquals("Failed to update the cartLine", true, cartLineDAO.updateCart(cart));
		
		
	}
}
