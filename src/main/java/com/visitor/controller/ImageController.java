package com.visitor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping("/api")
public class ImageController {
	
//	@PostMapping("/upload-image")
//	public String uploadImage(@RequestBody Map<String, String> payload)
//	{
//		try {
//			String base64Image = payload.get("image");
//			if(base64Image.contains(","))
//			{base64Image=base64Image.split(",")[1];}
//			
//			//byte[] imageBytes = Base64Utils.decodeFromString(base64Image);
//			byte[] imageBytes = Base64.getDecoder().decode(base64Image);
//			String fileName = "captured_" + System.currentTimeMillis() + ".png";
//			try(OutputStream stream = new FileOutputStream("uploads/" + fileName)) {
//                stream.write(imageBytes);
//            }
//			return "Image uploaded successfully: " + fileName;
//			
//		} catch (Exception e) {
//			return "Error uploading image: "+e.getMessage();
//			}
//
//}
	
//	@PostMapping("/api/visitor/uploadImage")
//	public ResponseEntity<String> uploadImage(@RequestBody ImageRequest request) {
//	    try {
//	        byte[] imageBytes = Base64.getDecoder().decode(request.getBase64Image());
//	        String fileName = "visitor_" + System.currentTimeMillis() + ".png";
//	        Path path = Paths.get("uploads/" + fileName);
//	        Files.write(path, imageBytes);
//
//	        return ResponseEntity.ok("Image saved successfully: " + fileName);
//	    } catch (Exception e) {
//	        return ResponseEntity.status(500).body("Error saving image: " + e.getMessage());
//	    }
//	}
//
//	@Data
//	class ImageRequest {
//	    private String base64Image;
//	}
	

	@PostMapping("/upload-image")
	public String uploadImage(@RequestBody Map<String, String> payload) {
	    try {
	        String base64Image = payload.get("image");
	        if (base64Image.contains(",")) {
	            base64Image = base64Image.split(",")[1];
	        }

	        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
	        String fileName = "captured_" + System.currentTimeMillis() + ".png";

	        // Ensure uploads folder exists
	        File uploadDir = new File("uploads");
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }

	        try (OutputStream stream = new FileOutputStream(new File(uploadDir, fileName))) {
	            stream.write(imageBytes);
	        }
	        return "Image uploaded successfully: " + fileName;

	    } catch (Exception e) {
	        return "Error uploading image: " + e.getMessage();
	    }
	}
}
