package com.visitor.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.entity.Visitor;
import com.visitor.repository.VisitorRepository;

@Service
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	private VisitorRepository visitorRepository;

	@Override
    public Visitor saveVisitor(Visitor visitor) {
        visitor.setVisitingDateAndTime(LocalDateTime.now());
        Visitor saved = visitorRepository.save(visitor);
        saved.setUnitNo("MCK/GP/25-26/" + String.format("%04d", saved.getId()));
        return visitorRepository.save(saved);
    }

	@Override
	public List<Visitor> getAllVisitors() {
		List<Visitor> allVisitors = visitorRepository.findAll();
		return allVisitors;
	}

	@Override
	public Visitor getVisitorById(Long loginId) {
		 Optional<Visitor> visitors = visitorRepository.findById(loginId);
		return visitors.orElse(null);
	}

}
