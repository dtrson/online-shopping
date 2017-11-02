package com.sonduong.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sonduong.shoppingbackend.dao.CategoryDAO;
import com.sonduong.shoppingbackend.dto.Category;

/**
 * @author Son Duong
 * 
 * 30.10.2017
 */
public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sonduong.shoppingbackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
//	
//	@Test
//	public void testAddCategory(){
//		category = new Category();
//		
//		category.setName("Laptop");
//		category.setDescription("This is some description for laptop");
//		category.setImageURL("CAT_1.png");
//		category.setActive(true);
//		assertEquals("Something went wrong", true, categoryDAO.add(category));
//		
//		category = new Category();
//		category.setName("Televison");
//		category.setDescription("This is some description for television");
//		category.setImageURL("CAT_2.png");
//		category.setActive(true);
//		assertEquals("Something went wrong", true, categoryDAO.add(category));
//		
//		category = new Category();
//		category.setName("Mobile");
//		category.setDescription("This is some description for Mobile");
//		category.setImageURL("CAT_3.png");
//		category.setActive(true);
//		assertEquals("Something went wrong", true, categoryDAO.add(category));
//	}
	
//	@Test
//	public void testGetCategory(){
//		category = categoryDAO.get(1);
//		assertEquals("Something went wrong", "Television", category.getName());
//	}
	
//	@Test
//	public void testUpdateCategory(){
//		category = categoryDAO.get(4);
//		category.setActive(true);
//		assertEquals("Something went wrong", true, categoryDAO.update(category));
//	}
	
//	@Test
//	public void testDeleteCategory(){
//		category = categoryDAO.get(1);
//		assertEquals("Something went wrong", true, categoryDAO.delete(category));
//	}
	
//	@Test
//	public void testListCategory(){
//		
//		assertEquals("Something went wrong", 2, categoryDAO.list().size());
//	}
	
	@Ignore
	@Test
	public void TestCRUDCategory(){
		
		//add operation
		category = new Category();
		category.setName("Smarthome");
		category.setDescription("This is some description for Smarthome");
		category.setImageURL("CAT_1.png");
		category.setActive(true);
		assertEquals("Something went wrong", true, categoryDAO.add(category));
		
		category = new Category();
		category.setName("Auto");
		category.setDescription("This is some description for Auto");
		category.setImageURL("CAT_2.png");
		category.setActive(true);
		assertEquals("Something went wrong", true, categoryDAO.add(category));
		
		
		//fetching and updating category
		category = categoryDAO.get(2);
		category.setName("Car");
		assertEquals("Something went wrong", true, categoryDAO.update(category));
		
		//delete category
		category = categoryDAO.get(1);
		assertEquals("Something went wrong", true, categoryDAO.delete(category));
		
		//fetching all categories
		assertEquals("Something went wrong", 1, categoryDAO.list().size());
		
	}
	
}
