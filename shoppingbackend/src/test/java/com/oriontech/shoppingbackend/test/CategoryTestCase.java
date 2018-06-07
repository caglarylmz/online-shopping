package com.oriontech.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.oriontech.shoppingbackend.dao.CategoryDAO;
import com.oriontech.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.oriontech.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	//CRUD Category
	@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Television");
		category.setDescription("Television Description");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully added a category inside the table", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Laptop");
		category.setDescription("Laptop Description");
		category.setImageURL("CAT_2.png");
		assertEquals("Successfully added a category inside the table", true, categoryDAO.add(category));
		
		category = new Category();
		category.setName("Desktop");
		category.setDescription("Desktop Description");
		category.setImageURL("CAT_3.png");
		assertEquals("Successfully added a category inside the table", true, categoryDAO.add(category));
		
	}

	@Test
	public void testGetCategory() {
		category = categoryDAO.get(2);
		assertEquals("Successfully fetched a single category from the table", "Laptop", category.getName());
	}

	@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(1);
		category.setName("TV");
		assertEquals("Successfully updated a category inside the table", true, categoryDAO.update(category));
	}

	@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(3);

		assertEquals("Successfully deactiveted a category inside the table", true, categoryDAO.delete(category));
	}

	@Test
	public void testGetCategoryList() {
		assertEquals("Successfully fetched the list of categories from the table", 2, categoryDAO.list().size());
	}

}
