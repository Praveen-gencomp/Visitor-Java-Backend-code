package com.visitor.services;

import java.util.List;

import com.visitor.entity.Visitor;

public interface VisitorService {
	
	 Visitor saveVisitor(Visitor visitor);
	    List<Visitor> getAllVisitors();
	    Visitor getVisitorById(Long loginId);


}
