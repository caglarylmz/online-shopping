package com.oriontech.shoppingbackend.dao;

import com.oriontech.shoppingbackend.dto.Address;
import com.oriontech.shoppingbackend.dto.Cart;
import com.oriontech.shoppingbackend.dto.User;

public interface UserDAO {
	
	//add an User
	boolean addUser(User user);
	//find user by email
	User getbyEmail(String email);
	//add an address
	boolean addAddress (Address address);
	/*
	//add a cart
	boolean addCart(Cart cart);
	*/
	//update a cart
	boolean updateCart(Cart cart);

}
