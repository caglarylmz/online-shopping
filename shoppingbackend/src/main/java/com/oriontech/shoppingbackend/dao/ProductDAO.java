package com.oriontech.shoppingbackend.dao;

import java.util.List;

import com.oriontech.shoppingbackend.dto.Product;

public interface ProductDAO {
	
	Product get(int productid);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	//business method
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
}
