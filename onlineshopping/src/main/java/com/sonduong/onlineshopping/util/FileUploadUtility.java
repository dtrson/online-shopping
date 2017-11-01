package com.sonduong.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Son Duong
 * 
 * 01.11.2017
 */
public class FileUploadUtility {
	
	private static final String ABS_PATH = "C:/Users/Son Duong/git/online-shopping/onlineshopping/src/main/webapp/assets/images/";
	private static String REAL_PATH = null;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		//get the real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/") + "\\";
		logger.info(REAL_PATH);
		
		//make sure all the directories exist
		if(!new File(ABS_PATH).exists()){
			//if not exist then create new directory
			new File(ABS_PATH).mkdirs();
		}
		
		if(!new File(REAL_PATH).exists()){
			//if not exist then create new directory
			new File(REAL_PATH).mkdirs();
		}
		
		try {	
			
			//project upload
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			logger.info("Adding image file to project path: "+ ABS_PATH + code + ".jpg");
			
//			//server upload
//			file.transferTo(new File(REAL_PATH + code + ".jpg"));
//			logger.info("Adding image file to server path: "+ REAL_PATH + code + ".jpg");
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
