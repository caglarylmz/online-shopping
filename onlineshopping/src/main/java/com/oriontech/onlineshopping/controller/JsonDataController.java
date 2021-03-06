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
	
	/*all/products yolu sadece data göndereceğinden ModelAndView yerine @ResponseBody kullanıyoruz.*/
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts(){
		return productDAO.listActiveProducts();
	}
	
	/*/admin/all/products yolu sadece data göndereceğinden ModelAndView yerine @ResponseBody kullanıyoruz.*/
	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin(){
		return productDAO.list();
	}
	
	//Categori id'ye göre data döner
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id ){
		return productDAO.listActiveProductsByCategory(id);
	}
}
