package com.oriontech.shoppingbackend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.oriontech.shoppingbackend.dao.ProductDAO;
import com.oriontech.shoppingbackend.dto.Product;

public class ProductTesCase {
	private static AnnotationConfigApplicationContext context;

	private static ProductDAO productDAO;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.oriontech.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}
	@Test
	public void testCRUDProduct() {
		//adding
		product = new Product();
		product.setName("Oppo Selfie s53");
		product.setBrand("Oppo");
		product.setDescription("Oppo Selfie Description");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setQuantity(10);
		product.setCategoryId(3);
		product.setSupplierId(3);		
		assertEquals("Something went wrong while inserting a new product",true,productDAO.add(product));
		
		//reading  (name is "samsung")
		product=productDAO.get(2);
		//updating("samsung" changes "Samsung Galaxy S7")
		product.setName("Samsung Galaxy S7");
		assertEquals("Something went wrong while updating the existing record",true,productDAO.update(product));
		
		//delete 
		assertEquals("Something went wrong while deleting the existing record",true,productDAO.delete(product));

		//list products
		assertEquals("Something went wrong while fetching the list of products",6,productDAO.list().size());
	}
	
	

}
