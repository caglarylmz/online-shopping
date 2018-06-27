package com.oriontech.onlineshopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oriontech.shoppingbackend.dao.CategoryDAO;
import com.oriontech.shoppingbackend.dao.ProductDAO;
import com.oriontech.shoppingbackend.dto.Category;
import com.oriontech.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false)String operation) {
	
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProduct",true);
		mv.addObject("title","Manage Products");
		
		Product mProduct= new Product();
		//set few of fields
		mProduct.setSupplierId(1);
		mProduct.setActive(true);
		mv.addObject("product",mProduct);
		//handleProductSubmission ile POST eilde operation==product ise if çalýþacaktýr.
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted Successfully!");
			}
		}
		
		return mv;
	}
	
	//returning categoires for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){			
		return categoryDAO.list();		
	}
	//handling product submission
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@ModelAttribute("product") Product product){
		logger.info(product.toString());
		
		productDAO.add(product);
		//eðer metot baþarýlý olursa operiton=product olur ve POST edilir. showManageProducts metodu ile (GET olduðundan bu PST mesaj alýnýr
		//operation parametreli bir GET metodur. handleProductSubmission ile operation POST edilir.
		return "redirect:/manage/products?operation=product";
	}
	
	

}
