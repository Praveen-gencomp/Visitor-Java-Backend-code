package com.visitor.services;

import java.io.IOException;
import java.time.LocalDate;
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
//	    List<Visitor> findByEntryDateBetween(LocalDate fromDate, LocalDate toDate);
	    //List<Visitor> getVisitorsByDate(LocalDate fromDate, LocalDate toDate);
	    List<Visitor> getVisitorsByDateRange(LocalDate fromDate, LocalDate toDate);

}
