package com.sonduong.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sonduong.shoppingbackend.dao.CategoryDAO;
import com.sonduong.shoppingbackend.dto.Category;

public class CategoryTestCase {
	
//	private static AnnotationConfigApplicationContext context;
//	
//	private static CategoryDAO categoryDAO;
//	
//	private Category category;
//	
//	@BeforeClass
//	public static void init(){
//		context = new AnnotationConfigApplicationContext();
//		context.scan("com.sonduong.shoppingbackend");
//		context.refresh();
//		
//		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
//	}
	
//	@Test
//	public void testAddCategory(){
//		category = new Category();
//		
//		category.setName("Mobile");
//		category.setDescription("This is some description for mobile");
//		category.setImageURL("CAT_1.png");
//		category.setActive(true);
//		assertEquals("Successfully added category to database", true, categoryDAO.add(category));
//		
//		category = new Category();
//		category.setName("Smarthome");
//		category.setDescription("This is some description for Smarthome");
//		category.setImageURL("CAT_2.png");
//		category.setActive(true);
//		assertEquals("Successfully added category to database", true, categoryDAO.add(category));
//		
//		category = new Category();
//		category.setName("Auto");
//		category.setDescription("This is some description for Auto");
//		category.setImageURL("CAT_3.png");
//		category.setActive(true);
//		assertEquals("Successfully added category to database", true, categoryDAO.add(category));
//	}
	
//	@Test
//	public void testGetCategory(){
//		category = categoryDAO.get(1);
//		assertEquals("Successfully fetched single category from database", "Television", category.getName());
//	}
	
//	@Test
//	public void testUpdateCategory(){
//		category = categoryDAO.get(4);
//		category.setActive(true);
//		assertEquals("Successfully updated single category in database", true, categoryDAO.update(category));
//	}
	
//	@Test
//	public void testDeleteCategory(){
//		category = categoryDAO.get(1);
//		assertEquals("Successfully delete single category in database", true, categoryDAO.delete(category));
//	}
	
//	@Test
//	public void testListCategory(){
//		
//		assertEquals("Successfully fetched list of category from database", 2, categoryDAO.list().size());
//	}
	
//	@Test
//	public void TestCRUDCategory(){
//		
//		//add operation
//		category = new Category();
//		category.setName("Smarthome");
//		category.setDescription("This is some description for Smarthome");
//		category.setImageURL("CAT_1.png");
//		category.setActive(true);
//		assertEquals("Successfully added category to database", true, categoryDAO.add(category));
//		
//		category = new Category();
//		category.setName("Auto");
//		category.setDescription("This is some description for Auto");
//		category.setImageURL("CAT_2.png");
//		category.setActive(true);
//		assertEquals("Successfully added category to database", true, categoryDAO.add(category));
//		
//		
//		//fetching and updating category
//		category = categoryDAO.get(2);
//		category.setName("Car");
//		assertEquals("Successfully updated single category in database", true, categoryDAO.update(category));
//		
//		//delete category
//		category = categoryDAO.get(1);
//		assertEquals("Successfully delete single category in database", true, categoryDAO.delete(category));
//		
//		//fetching all categories
//		assertEquals("Successfully fetched list of category from database", 1, categoryDAO.list().size());
//		
//	}
	
}
