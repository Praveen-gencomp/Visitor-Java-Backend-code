package com.visitor.services;

import java.io.IOException;
import java.util.List;

import com.visitor.entity.Visitor;

import VisitorDTO.VisitorRequest;
import VisitorDTO.VisitorResponse;
import VisitorDTO.VisitorResponseDTO;

public interface VisitorService {
	
//	 Visitor saveVisitor(Visitor visitor);
//	Visitor saveVisitor(Visitor visitor, MultipartFile image,Map<String, String> payload);
//	 Visitor saveVisitor(Visitor visitor, MultipartFile image) throws IOException;
	    List<Visitor> getAllVisitors();
	    Visitor getVisitorById(long id);
	    
	    Visitor saveFromBase64(VisitorRequest req);
	    
	    VisitorResponseDTO getVisitorByUnitNo(String unitNo) throws IOException;


}
