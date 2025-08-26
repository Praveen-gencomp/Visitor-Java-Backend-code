package com.visitor.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

	 public String saveBase64Image(String base64Image) throws Exception {
	        if (base64Image == null) {
	            return null;
	        }

	        if (base64Image.contains(",")) {
	            base64Image = base64Image.split(",")[1];
	        }

	        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
	        String fileName = "img_" + System.currentTimeMillis() + ".png";

	        File uploadDir = new File("uploads");
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }

	        try (OutputStream os = new FileOutputStream(new File(uploadDir, fileName))) {
	            os.write(imageBytes);
	        }

	        return fileName;  // ✅ return only file name
	    }
}
