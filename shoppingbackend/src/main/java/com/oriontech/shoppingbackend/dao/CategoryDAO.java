package com.oriontech.shoppingbackend.dao;

import java.util.List;

import com.oriontech.shoppingbackend.dto.Category;

public interface CategoryDAO {
	//get categories
	List<Category> list();
	//get category item from db
	Category get(int id);
	//add category item database
	boolean add(Category category);
	//update category item from database
	boolean update(Category category);
	//delete item from database
	boolean delete(Category category);
}
