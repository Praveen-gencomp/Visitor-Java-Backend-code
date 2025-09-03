package com.visitor.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visitor.entity.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
//	Visitor findByUnitNo(String unitNo);
	Optional<Visitor> findByUnitNo(String unitNo);
//	 List<Visitor> findByVisitingDateAndTimeBetween(LocalDateTime start, LocalDateTime end);
	@Query("SELECT v FROM Visitor v WHERE v.visitingDateAndTime BETWEEN :start AND :end")
    List<Visitor> findVisitorsBetweenDates(@Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end);
	 

	

}
