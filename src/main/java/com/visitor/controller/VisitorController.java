package com.visitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.entity.Visitor;
import com.visitor.services.VisitorService;

@RestController
@RequestMapping("/api/visitors")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitorController {

	@Autowired
	private VisitorService visitorService;

	@PostMapping
	public ResponseEntity<Visitor> saveVisitor(@RequestBody Visitor visitor) {
		Visitor saveVisitor = visitorService.saveVisitor(visitor);

		return new ResponseEntity<Visitor>(saveVisitor, HttpStatus.CREATED);
	}

	@GetMapping
	public List<Visitor> getAllVisitors() {
		return visitorService.getAllVisitors();
	}

	@GetMapping("/{id}")
	public Visitor getVisitorById(@PathVariable Long id) {
		return visitorService.getVisitorById(id);
	}

}
