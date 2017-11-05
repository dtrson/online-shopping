package com.sonduong.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonduong.onlineshopping.model.UserModel;
import com.sonduong.shoppingbackend.dao.CartLineDAO;
import com.sonduong.shoppingbackend.dao.ProductDAO;
import com.sonduong.shoppingbackend.dto.Cart;
import com.sonduong.shoppingbackend.dto.CartLine;
import com.sonduong.shoppingbackend.dto.Product;

/**
 * @author Son Duong
 * 
 * 05.11.2017
 */
@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	//return the cart of the user who has logged in
	private Cart getCart(){
		return ((UserModel) session.getAttribute("userModel")).getCart();
		
	}
	
	//return the entire cartlines
	public List<CartLine> getCartLines(){
		Cart cart = this.getCart();
		return cartLineDAO.list(cart.getId());
	}

	public String updateCartLine(int cartLineId, int count) {
		
		//fetch the cartLine
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null){
			return "result=error";
		}
		else{
			
			Product product = cartLine.getProduct();
			double oldCartLineTotal = cartLine.getTotal();
			int oldCount = cartLine.getProductCount();
			
			if(product.getQuantity()<=count){
				count = product.getQuantity();
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			
			//update cartLine
			cartLineDAO.update(cartLine);
			
			//update new product count
			if(count > oldCount){
				product.setQuantity(product.getQuantity() - (count-oldCount));
			}else{
				product.setQuantity(product.getQuantity() + (oldCount-count));
			}
			productDAO.update(product);
			
			//update the cart also
			Cart cart = this.getCart();
			cart.setGrandTotal((cart.getGrandTotal() - oldCartLineTotal) + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}

	}

	public String deleteCartLine(int cartLineId) {
		
		//fetch the cartLine
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null){
			return "result=error";
		}
		else{
			//fetch the product
			Product product = cartLine.getProduct();
			
			//update the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			cartLineDAO.updateCart(cart);
			
			//remove the cartLine
			cartLineDAO.delete(cartLine);
			
			//increase again the product count after removing them from the cartLine and update
			product.setQuantity(product.getQuantity() + cartLine.getProductCount());
			productDAO.update(product);
			
			return "result=deleted";
		}

	}

	public String addCartLine(int productId) {
		
		String response = null;
		
		Cart cart = this.getCart();
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		//case that the cartLine for this product is not available
		if(cartLine == null){
			
			//add a new cartLine
			cartLine = new CartLine();
			
			//fetch the product
			Product product = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(cartLine.getBuyingPrice() * cartLine.getProductCount());
			cartLine.setAvailable(true);
			cartLineDAO.add(cartLine);
			
			//remove the product quantity by 1 and update new product
			product.setQuantity(product.getQuantity() - 1);
			productDAO.update(product);
			
			//update cart also
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}
		//case that the cartLine for this product is already available
		else{
			
			//fetch the product
			Product product = productDAO.get(productId);
			
			//update the cartLine for this product
			//check if the available count for this product before adding
			if(product.getQuantity() >= 1){
				cartLine.setProductCount(cartLine.getProductCount() + 1);
				cartLine.setTotal(cartLine.getBuyingPrice() * cartLine.getProductCount());
				cartLineDAO.update(cartLine);
				
				//remove the product quantity by 1 and update new product
				product.setQuantity(product.getQuantity() - 1);
				productDAO.update(product);
				
				
				//update the cart also
				cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
				cartLineDAO.updateCart(cart);
				
				response = "result=added";
			}
			else{
				response = "result=soldOut";
			}
			
		}
		
		
		return response;
	}
}
