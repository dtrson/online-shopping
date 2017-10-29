package com.sonduong.shoppingbackend.dao;

import java.util.List;

import com.sonduong.shoppingbackend.dto.Category;

/**
 * @author Son Duong
 * 
 * 29.10.2017
 */
public interface CategoryDAO {
	
	boolean add(Category category);
	
	List<Category> list();
	Category get(int id);

}
