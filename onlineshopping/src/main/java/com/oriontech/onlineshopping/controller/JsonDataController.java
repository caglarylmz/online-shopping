package com.oriontech.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oriontech.shoppingbackend.dao.ProductDAO;
import com.oriontech.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	
	@Autowired
	private ProductDAO productDAO;
	
	/*all/products yolu sadece data g�nderece�inden ModelAndView yerine @ResponseBody kullan�yoruz.*/
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts(){
		return productDAO.listActiveProducts();
	}
	//Categori id'ye g�re data d�ner
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id ){
		return productDAO.listActiveProductsByCategory(id);
	}
}
