package com.sonduong.shoppingbackend.dao;

import java.util.List;

import com.sonduong.shoppingbackend.dto.Cart;
import com.sonduong.shoppingbackend.dto.CartLine;

/**
 * @author Son Duong
 * 
 * 05.11.2017
 */
public interface CartLineDAO {
	
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	public List<CartLine> list(int cartId);
	
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId, int productId);
	
	public boolean updateCart(Cart cart);
}
