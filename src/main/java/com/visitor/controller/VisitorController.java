package com.visitor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.visitor.entity.Visitor;
import com.visitor.services.ImageService;
import com.visitor.services.VisitorService;

import VisitorDTO.VisitorRequest;
import VisitorDTO.VisitorResponse;
import VisitorDTO.VisitorResponseDTO;

@RestController
@RequestMapping("/api/visitors")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitorController {

	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private ImageService imageService;

//	@PostMapping
//    public ResponseEntity<Visitor> saveVisitor(@RequestBody Visitor visitor) {
//        try {
//            // ✅ Save image from visitor (assuming visitor.imageName contains base64 from Angular)
//            if (visitor.getImageName() != null && visitor.getImageName().startsWith("data:image")) {
//            	String fileName  = imageService.saveBase64Image(visitor.getImageName());
//            	visitor.setImageName(fileName);
//            }
//            Visitor savedVisitor = visitorService.saveVisitor(visitor);
//            return new ResponseEntity<>(savedVisitor, HttpStatus.CREATED);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
	
//	@PostMapping
//	public ResponseEntity<Visitor> saveVisitor(@RequestBody Visitor visitor) {
//		Visitor saveVisitor = visitorService.saveVisitor(visitor);
//
//		return new ResponseEntity<Visitor>(saveVisitor, HttpStatus.CREATED);
//	}

	@GetMapping
	public List<Visitor> getAllVisitors() {
		return visitorService.getAllVisitors();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Visitor> getVisitorById(@PathVariable Long id) {
		Visitor visitorById = visitorService.getVisitorById(id);
		return new ResponseEntity<Visitor>(visitorById, HttpStatus.OK); 	
		}
	
	// for image uploading
//	@PostMapping
//    public String saveVisitor(@RequestBody Map<String, String> payload, @RequestBody Visitor visitor) {
//        try {
//            String base64Image = payload.get("image");
//
//            // Remove prefix if present
////            if (base64Image.contains(",")) {
////                base64Image = base64Image.split(",")[1];
////            }
//            if (base64Image != null) {
//                if (base64Image.contains(",")) {
//                    base64Image = base64Image.split(",")[1]; // case: with prefix
//                }}
//
//            // Decode Base64
//            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
//
//            // Save file
//            String fileName = "img_" + System.currentTimeMillis() + ".png";
//            File uploadDir = new File("uploads");
//            if (!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//
//            File file = new File(uploadDir, fileName);
//            try (OutputStream os = new FileOutputStream(file)) {
//                os.write(imageBytes);
//            }
////         // Save file locally
////            try (OutputStream stream = new FileOutputStream(new File(uploadDir, fileName))) {
////                stream.write(imageBytes);
////            }
//
//            // Optional: save path in DB (pseudo code)
//            //imageRepository.save(new ImageEntity(fileName, file.getAbsolutePath()));
//
//            //return "Image uploaded successfully: " + file.getAbsolutePath();
//            visitorService.saveVisitor(visitor);
//            return "Image uploaded successfully: " + file;
//            
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error uploading image: " + e.getMessage();
//        }
//    }

//	@PostMapping
//    public ResponseEntity<Visitor> registerVisitor( MultipartFile image,
//            @ModelAttribute Visitor visitor) throws Exception {
//		Visitor saveVisitor = visitorService.saveVisitor(visitor, image);
//        return ResponseEntity.ok(saveVisitor);
//    }
	
//	@PostMapping
//	public ResponseEntity<String> saveVisitor(@RequestBody Visitor visitor, MultipartFile image) throws IOException {
//	    // Decode Base64 and save file
//	    String base64Image = visitor.getCapturedImage().split(",")[1];
//	    byte[] imageBytes = Base64.getDecoder().decode(base64Image);
//
//	    String fileName = "visitor_" + System.currentTimeMillis() + ".png";
//	    Path path = Paths.get("uploads/" + fileName);
//	    Files.write(path, imageBytes);
//
//	    // Save visitor info + file path to DB
//	    Visitor saveVisitor = visitorService.saveVisitor(visitor, image);
//
//	    return ResponseEntity.ok("Visitor saved successfully!");
//	}
	
	// POST JSON that includes Base64 under 'capturedImage'
    @PostMapping
    public VisitorResponse register(@RequestBody VisitorRequest req) {
        Visitor saved = visitorService.saveFromBase64(req);
        String unitNo = (saved.getUnitNo() != null && !saved.getUnitNo().isBlank())
                ? saved.getUnitNo()
                : ("GEN/" + saved.getId());
        return new VisitorResponse(
                saved.getId(),
                "Visitor registered successfully",
                unitNo,
                saved.getFileName(),
                saved.getImagePath()
        );
    }
	
    //Get Visitor by UnitNo
    @GetMapping("/unit")
    //@GetMapping("unit/{unitNo}")
    public ResponseEntity<VisitorResponseDTO> getVisitorByUnitNo(@RequestParam  String unitNo) throws IOException {
        return ResponseEntity.ok(visitorService.getVisitorByUnitNo(unitNo));
    }
    
    @GetMapping("/filter")
    public List<Visitor> getReportByDate(
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        return visitorService.getVisitorsByDateRange(fromDate, toDate);
    }
}
