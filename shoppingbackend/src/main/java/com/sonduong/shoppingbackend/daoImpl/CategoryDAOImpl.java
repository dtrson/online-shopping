package com.sonduong.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sonduong.shoppingbackend.dao.CategoryDAO;
import com.sonduong.shoppingbackend.dto.Category;
import com.sonduong.shoppingbackend.dto.Product;

/**
 * @author Son Duong
 * 
 * 29.10.2017
 */
@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Category> list() {
		
		/**	create HQL Hibernate Query Language where Category is Entity's Name, not Table's Name, 
			but default is Category's Name = Table'Name**/
		return sessionFactory.getCurrentSession().createQuery("FROM Category",Category.class).getResultList();
	}

	//getting single category based on id
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	//add single category
	@Override
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

	//update single category
	@Override
	public boolean update(Category category) {
		
		try {
			//update the category to database
			sessionFactory.getCurrentSession().update(category);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		
		
		
		try {
			category.setActive(false);
			sessionFactory.getCurrentSession().update(category);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Category> listActiveCategories() {
		
		String selectActiveCategories = "FROM Category WHERE active = :active";
		
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveCategories, Category.class)
				.setParameter("active", true)
				.getResultList();
	}

}
