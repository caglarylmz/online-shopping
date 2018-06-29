package com.oriontech.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oriontech.onlineshopping.util.FileUploadUtility;
import com.oriontech.onlineshopping.validator.ProductValidator;
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
		//handling product submission
		@RequestMapping(value="/products",method=RequestMethod.POST)
		public String handleProductSubmission(@Valid @ModelAttribute("product") Product product, BindingResult results, Model model, HttpServletRequest req){
			
			//for file upload validator with spring validator
			new ProductValidator().validate(product,results);			
			
			//check if there are any errors - Hibernate validator
			if(results.hasErrors()) {
				//Submit butonuna bastýðýmýzda, eðer uygun olmayan bir validate varsa userClickManageProduct çalþýr ve page.jsp hata mesajlarý ile yüklenir
				model.addAttribute("userClickManageProduct",true);
				model.addAttribute("title","Manage Products");
				model.addAttribute("message","Validation failed for Product Submission!");
				return "page";
			}
			
			
			logger.info(product.toString());
			
			productDAO.add(product);
			
			if(!product.getFile().getOriginalFilename().equals("")) {
				FileUploadUtility.uploadFile(req, product.getFile(), product.getCode());
			}
			
			//eðer metot baþarýlý olursa operiton=product olur ve POST edilir. showManageProducts metodu ile (GET olduðundan bu POST mesaj alýnýr
			//operation parametreli bir GET metodur. handleProductSubmission ile operation POST edilir.
			return "redirect:/manage/products?operation=product";
		}
	
	//returning categoires for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){			
		return categoryDAO.list();		
	}
	
	
	

}
