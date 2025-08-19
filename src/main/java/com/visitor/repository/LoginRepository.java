package com.visitor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visitor.entity.LoginUser;
@Repository
public interface LoginRepository extends JpaRepository<LoginUser, Long>{
	
	Optional<LoginUser> findByLoginId(String loginId);

}
