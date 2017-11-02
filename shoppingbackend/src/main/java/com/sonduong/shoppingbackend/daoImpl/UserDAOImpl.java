package com.sonduong.shoppingbackend.daoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sonduong.shoppingbackend.dao.UserDAO;
import com.sonduong.shoppingbackend.dto.Address;
import com.sonduong.shoppingbackend.dto.Cart;
import com.sonduong.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		
		try {
			
			return sessionFactory.getCurrentSession().createQuery(selectQuery,User.class).setParameter("email", email).getSingleResult();
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
