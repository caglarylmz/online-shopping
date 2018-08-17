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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
			}else if(operation.equals("category")) {
				mv.addObject("message","Category Submitted Successfully!");
			}
		}		
		return mv;
	}
	//get product details for edit
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)//manageproduct.jsp method POST vermiþtik
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct",true);	//look page.jsp
		mv.addObject("title","Manage Products");
		//fetch the Product as id
		Product mProduct= productDAO.get(id);
		//set fields
		mv.addObject("product",mProduct);		//manageproduct.jsp'de modelAttribute="product" olarak vermiþtik.
		return mv;
	}
	
	//handling product submission
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product product, BindingResult results, Model model, HttpServletRequest req){
		
		//for file upload(image) validator with spring validator
		if(product.getId()==0) {		//yeni eklenen product ise 
			new ProductValidator().validate(product,results);	
		}else {//var olan product ise
			if(!product.getFile().getOriginalFilename().equals(""))// fakat daha önce image eklenmemiþ ise 
				new ProductValidator().validate(product,results);	
		}
		//check if there are any errors - Hibernate validator
		if(results.hasErrors()) {
			//Submit butonuna bastýðýmýzda, eðer uygun olmayan bir validate varsa userClickManageProduct çalýþýr ve page.jsp hata mesajlarý ile yüklenir
			model.addAttribute("userClickManageProduct",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation failed for Product Submission!");
			return "page";
		}
				
		logger.info(product.toString());
		//create a new product record
		if(product.getId()==0) {
			productDAO.add(product);
		}else {//if use edit button , we cant add a new product. we have to update.
			productDAO.update(product);
		}
		if(!product.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(req, product.getFile(), product.getCode());
		}	
		//eðer metot baþarýlý olursa operiton=product olur ve POST edilir. showManageProducts metodu ile (GET olduðundan bu POST mesaj alýnýr
		//operation parametreli bir GET metodur. handleProductSubmission ile operation POST edilir.
		return "redirect:/manage/products?operation=product";
	}
	
		
	//product activate / deactivate
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		//id'ye göre db'deki ilgili product getirilir.
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		//if product active set our product deactive , if product deactive set our product active
		product.setActive(!product.isActive());
		//update db->product with our product
		productDAO.update(product);
		
		//"/product/{id}/activation" web page message for product activation
		return (isActive)?
				"You have succesfully deactivated the product with id " + product.getId()
				:
				"You have succesfully activated the product with id " + product.getId();
	}
	
	//to handle category subission/add category ile post edilen category nesnesini tutar
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
		//showManageProducts()'da if-else bloðuna operation.equals("category") seçeneði eklenir. Böylece handleCategorySubmission() baþarýlý ise kullanýcaya möesaj iletilir. 
	}
	
	
	//returning categoires for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){	
		return categoryDAO.list();		
	}	
	
	//for add category dialog box
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}
