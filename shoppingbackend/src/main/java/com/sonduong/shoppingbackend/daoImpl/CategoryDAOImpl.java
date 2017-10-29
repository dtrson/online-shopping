package com.sonduong.shoppingbackend.daoImpl;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sonduong.shoppingbackend.dao.CategoryDAO;
import com.sonduong.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static List<Category> categories = new ArrayList<Category>() ;
	
	static{
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some description for television");
		category.setImageURL("CAT_1.png");
		category.setActive(true);
		categories.add(category);
		
		Category category2 = new Category();
		category2.setId(2);
		category2.setName("Mobile");
		category2.setDescription("This is some description for mobile");
		category2.setImageURL("CAT_2.png");
		category2.setActive(true);
		categories.add(category2);
		
		Category category3 = new Category();
		category3.setId(3);
		category3.setName("Laptop");
		category3.setDescription("This is some description for laptop");
		category3.setImageURL("CAT_3.png");
		category3.setActive(true);
		categories.add(category3);
		
	}
	
	@Override
	public List<Category> list() {
		return categories;
	}

	@Override
	public Category get(int id) {
		
		for(Category category : categories){
			if(category.getId() == id){
				return category;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public boolean add(Category category) {
		
		try {
			//add the category to database
			sessionFactory.getCurrentSession().persist(category);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
