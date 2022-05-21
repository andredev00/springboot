package com.spring.andre.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.andre.demo.service.AmazonService;

@RestController
@RequestMapping("/storage/")
public class AmazonController {

	private AmazonService amazonClient;

	@Autowired
     void BucketController(AmazonService amazonClient) {
        this.amazonClient = amazonClient;
    }

	@PostMapping("uploadFile")
	public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
		return this.amazonClient.uploadFile(file);
	}

	@DeleteMapping("deleteFile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}

}
