package com.oriontech.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.oriontech.shoppingbackend.dao.UserDAO;
import com.oriontech.shoppingbackend.dto.Address;
import com.oriontech.shoppingbackend.dto.Cart;
import com.oriontech.shoppingbackend.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address=null;
	
	@BeforeClass
	public static void init() {
		context=new AnnotationConfigApplicationContext();
		context.scan("com.oriontech.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
/*	
	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("Çağlar");
		user.setLastName("YILMAZ");
		user.setEmail("caglar.ylmz@outlook.com");
		user.setContactNumber("05558317824");
		user.setRole("USER");
		user.setPassword("123456");
		//add user
		assertEquals("Failed add user",true,userDAO.addUser(user));
		
		address=new Address();
		address.setAddressLineOne("2579 Sokak No:33");
		address.setAddressLineTwo("Gültepe");
		address.setState("Konak");
		address.setCity("İzmir");
		address.setPostalCode("35130");
		address.setBilling(true);
		
		//link the user with the address using user id
		address.setUserId(user.getId());
		
		//add the address
		assertEquals("Failed to add address!",true,userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")) {
			//create a chart for this user
			cart = new Cart();
			cart.setUser(user);
			
			//add the chart
			assertEquals("Failed to add cart!",true, userDAO.updateCart(cart));
			
			//add shipping address this user
			address=new Address();
			address.setAddressLineOne("2579 Sokak No:33");
			address.setAddressLineTwo("Gültepe");
			address.setState("Konak");
			address.setCity("İzmir");
			address.setPostalCode("35130");
			address.setBilling(true);
			address.setShipping(true);
			
			//add the shipping address
			assertEquals("Failed to add Shipping address",true,userDAO.addAddress(address) );
		}
		
		
		    
		
	}*/
/*
	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("Çağlar");
		user.setLastName("YILMAZ");
		user.setEmail("caglar.ylmz@outlook.com");
		user.setContactNumber("05558317824");
		user.setRole("USER");
		user.setPassword("123456");
		if(user.getRole().equals("USER")) {
			//create a chart for this user
			cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		//add user
				assertEquals("Failed add user",true,userDAO.addUser(user));
				
	}*/
	@Test
	public void testUpdateCart() {
		//email'e göre user elde ediliyor
		user=userDAO.getbyEmail("caglar.ylmz@outlook.com");
		cart= user.getCart();
		
		cart.setGrandTotal(5555);
		cart.setCartLines(2);
		
		assertEquals("Failed to update the cart",true,userDAO.updateCart(cart));
		
	}
}
