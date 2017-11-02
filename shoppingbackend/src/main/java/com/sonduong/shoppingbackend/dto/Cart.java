package com.sonduong.shoppingbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author Son Duong
 * 
 * 02.11.2017
 */
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
//	@Column(name="user_id")
//	private int userId;
	
	@OneToOne //the default name of this filed in the database would be the field name: "user" + "_" + the primary key of this user table: "id" 
	@JoinColumn(name="user_id") //change the name of this field in the database
	private User user;
	
	@Column(name="cart_lines")
	private int cartLines;
	
	@Column(name="grand_total")
	private double grandTotal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCartLines() {
		return cartLines;
	}

	public void setCartLines(int cartLines) {
		this.cartLines = cartLines;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", cartLines=" + cartLines + ", grandTotal=" + grandTotal + "]";
	}

}
