package com.sonduong.shoppingbackend.dao;

import java.util.List;

import com.sonduong.shoppingbackend.dto.Category;

/**
 * @author Son Duong
 * 
 * 29.10.2017
 */
public interface CategoryDAO {
	
	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}
