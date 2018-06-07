package com.oriontech.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oriontech.shoppingbackend.dao.CategoryDAO;
import com.oriontech.shoppingbackend.dto.Category;

@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		
		//passing the list of categories
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickHome",true);
		return mv;
	}
	//about
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	/*//listProducts
	@RequestMapping(value = "/listProducts")
	public ModelAndView products() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","All Products");
		mv.addObject("userClickProducts",true);
		return mv;
	}*/
	//contact
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true);
		return mv;
	}
	
	/*Methods to load allProudcts*/
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","All Products");
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	/*Methods to load dynamic category Proudcts*/
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		//CategoryDAO to fetch a single category
		Category category =null;
		category= categoryDAO.get(id);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title",category.getName());
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("category",category);
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	
}

