package com.oriontech.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	private static final String ABSULUTE_PATH="D:\\WorkSpace\\Spring\\online-shopping\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	private static  String REAL_PATH="";
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	public static void uploadFile(HttpServletRequest req, MultipartFile file, String code) {
		//get the real path
		REAL_PATH = req.getSession().getServletContext().getRealPath("/assets/images/");
		
		logger.info(REAL_PATH);
		
		//to make sure all the directory exists		
		if(!new File(ABSULUTE_PATH).exists()) {
			//create directory
			new File(ABSULUTE_PATH).mkdirs();
		}
		if(!new File(REAL_PATH).exists()) {
			//create directory
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			//server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			//project directory upload
			file.transferTo(new File(ABSULUTE_PATH + code + ".jpg "));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
