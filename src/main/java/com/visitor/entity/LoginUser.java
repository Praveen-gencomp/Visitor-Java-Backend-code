package com.visitor.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LoginUser {
	
	    @Id
	    private String loginId;
	    private String password;
	    private String userName;
	    private String role;
	    @Column(name = "created_date")
	    private LocalDateTime createdDate;
	    
	    @PrePersist
	    protected void onCreate() {
	        this.createdDate = LocalDateTime.now();
	    }

	    

}
