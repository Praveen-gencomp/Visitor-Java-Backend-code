package com.visitor.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.visitor.entity.Visitor;
import com.visitor.repository.VisitorRepository;

import VisitorDTO.VisitorRequest;
import VisitorDTO.VisitorResponse;
import VisitorDTO.VisitorResponseDTO;

@Service
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	private VisitorRepository visitorRepository;
//	private final String uploadDir = "uploads/";
//	private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

//	@Override
//    public Visitor saveVisitor(Visitor visitor) {
//        visitor.setVisitingDateAndTime(LocalDateTime.now());
//        Visitor saved = visitorRepository.save(visitor);
//        saved.setUnitNo("MCK/GP/25-26/" + String.format("%04d", saved.getId()));
//        return visitorRepository.save(saved);
//    }

	// Get ALL 
	@Override
	public List<Visitor> getAllVisitors() {
		List<Visitor> allVisitors = visitorRepository.findAll();
		return allVisitors;
	}

	// Get by ID
	@Override
	public Visitor getVisitorById(long id) {
		
		return visitorRepository.findById(id).get();
	}
//	@Override
//	public Visitor getVisitorByUnitNo(String unitNo)throws IOException {
//		Visitor visitor = visitorRepository.findByUnitNo(unitNo)
//	            .orElseThrow(() -> new RuntimeException("Visitor not found for unitNo: " + unitNo));
//
//	    VisitorResponse2 response = new VisitorResponse2();
//	    BeanUtils.copyProperties(visitor, response);
//
//	    // Load image if present
//	    if (visitor.getFileName() != null) {
//	        Path imagePath = Paths.get("uploads").resolve(visitor.getFileName());
//	        if (Files.exists(imagePath)) {
//	            byte[] imageBytes = Files.readAllBytes(imagePath);
//	            response.setCapturedImage("data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes));
//	        }
//	    }
//	    return visitor;
//	}
	
	 // configurable upload dir; default to "<projectRoot>/uploads"
    @Value("${app.upload-dir:#{systemProperties['user.dir'] + '/uploads'}}")
    private String uploadDir;
    
    public VisitorServiceImpl(VisitorRepository visitorRepository)
    {
    	this.visitorRepository=visitorRepository;
    }
    
	
    
	// Save data and Image
    @Override
    public Visitor saveFromBase64(VisitorRequest req) {
        Visitor v = new Visitor();
        BeanUtils.copyProperties(req, v);

        // Save file if present
        if (req.getCapturedImage() != null && !req.getCapturedImage().isBlank()) {
            try {
                final String dataUrl = req.getCapturedImage().trim();
                final String base64Data = dataUrl.contains(",")
                        ? dataUrl.substring(dataUrl.indexOf(",") + 1) // strip "data:image/png;base64,"
                        : dataUrl;

                byte[] bytes = Base64.getDecoder().decode(base64Data);

                // Ensure dir exists
                Path dir = Paths.get(uploadDir);
                if (!Files.exists(dir)) {
                    Files.createDirectories(dir);
                }
//                String fileName = "captured_" + System.currentTimeMillis() + ".png";
                String fileName = System.currentTimeMillis() + "_visitor.png";
                Path filePath = dir.resolve(fileName);

                Files.write(filePath, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                v.setFileName(fileName);
                v.setImagePath(filePath.toAbsolutePath().toString());

            } catch (IllegalArgumentException | IOException e) {
                // If decoding/writing fails, proceed without image but DO log
                System.err.println("Failed to save image: " + e.getMessage());
            }
        }
        v.setVisitingDateAndTime(LocalDateTime.now());
      Visitor saved = visitorRepository.save(v);
      saved.setUnitNo("MCK/GP/25-26/" + String.format("%04d", saved.getId()));
        return visitorRepository.save(v);
    }

    private Boolean bool(Boolean b) { return b != null ? b : Boolean.FALSE; 
   }


    
	@Override
	public VisitorResponseDTO  getVisitorByUnitNo(String unitNo) throws IOException {
		Visitor visitor = visitorRepository.findByUnitNo(unitNo)
                .orElseThrow(() -> new RuntimeException("Visitor not found for unitNo: " + unitNo));

       // VisitorRequest req = new VisitorRequest();
		VisitorResponseDTO responseDto = new VisitorResponseDTO();
        BeanUtils.copyProperties(visitor, responseDto);

        // Read the image from uploads folder
        if (visitor.getFileName() != null) {
            Path imagePath = Paths.get("uploads").resolve(visitor.getFileName());
            byte[] imageBytes = Files.readAllBytes(imagePath);
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            responseDto.setCapturedImage("data:image/png;base64," + base64Image);
           // visitor.seti("data:image/png;base64," + base64Image);
        }
        return responseDto;
    }

	
	}


