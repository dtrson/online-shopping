package com.sonduong.shoppingbackend.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sonduong.shoppingbackend.dao.CategoryDAO;
import com.sonduong.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Category> list() {
		
		/**	create HQL Hibernate Query Language where Category is Entity's Name, not Table's Name, 
			but default is Category's Name = Table'Name**/
		String selectActiveCategory = "FROM Category WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		
		return query.getResultList();
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
			//add the category to database
			sessionFactory.getCurrentSession().update(category);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		
		category.setActive(false);
		
		try {
			//add the category to database
			sessionFactory.getCurrentSession().update(category);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
