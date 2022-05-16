package com.spring.andre.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AmazonService {
	
		private static AmazonS3 amazons3;
	
	 	public static File convertMultiPartToFile(MultipartFile file) throws IOException {
	        File convFile = new File(file.getOriginalFilename());
	        FileOutputStream fos = new FileOutputStream(convFile);
	        fos.write(file.getBytes());
	        fos.close();
	        return convFile;
	    }
	 	
	 	//generate unique name to the uploadfile
	    public static String generateFileName(MultipartFile multiPart) {
	        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	    }

	    //upload files to s3
	    public static void uploadFileTos3bucket(String fileName, File file) {
	    	amazons3.putObject(new PutObjectRequest("", fileName, file)
	                .withCannedAcl(CannedAccessControlList.PublicRead));
	    }

}
