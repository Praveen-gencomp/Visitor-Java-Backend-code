package com.visitor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visitor.entity.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
//	Visitor findByUnitNo(String unitNo);
	Optional<Visitor> findByUnitNo(String unitNo);

}
