package com.sonduong.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sonduong.shoppingbackend.dao.ProductDAO;
import com.sonduong.shoppingbackend.dto.Product;

/**
 * @author Son Duong
 * 
 * 30.10.2017
 */
public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDAO;
	
	private Product product;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sonduong.shoppingbackend");
		context.refresh();
		
		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	@Ignore
	@Test
	public void TestCRUDCategory(){
		
		//add operation
		product = new Product();
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some description for oppo mobile phone");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		assertEquals("Something went wrong while inserting new product", true, productDAO.add(product));
		
		
		//fetching and updating product
		product = productDAO.get(2);
		product.setName("Samsung Galaxy S7");
		assertEquals("Something went wrong while updating product", true, productDAO.update(product));
		
		//delete category
		product = productDAO.get(1);
		assertEquals("Something went wrong while deleting product", true, productDAO.delete(product));
		
		//fetching all categories
		assertEquals("Something went wrong while listing products", 6, productDAO.list().size());
		
	}

	@Ignore
	@Test
	public void testListActiveProducts(){
		assertEquals("Something went wrong while listing products", 5, productDAO.listActiveProducts().size());
	}
	
	@Ignore
	@Test
	public void testListActiveProductsByCategory(){
		assertEquals("Something went wrong while listing products", 3, productDAO.listActiveProductsByCategory(3).size());
	}
	
	@Ignore
	@Test
	public void testGetLatestActiveProducts(){
		assertEquals("Something went wrong while listing products", 3, productDAO.getLatestActiveProducts(3).size());
	}
	
}
