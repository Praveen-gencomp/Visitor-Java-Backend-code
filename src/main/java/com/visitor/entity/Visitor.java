package com.visitor.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visitor {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    //Visitor Information
	    private String identificationNo;
	    private String visitorName;
	    private String gender;
	    private String documentType;
	    private String licenseNumber;
	    private LocalDate expiryDate;
	    //Visitor Address
	    private String address;
	    private String town;
	    private String state;
	    private String postcode;
	    private String country;
	    //Other Details
	    private String vehicleNumber;
	    private String vehicleType;
	    private String company;
	    //Visit Details
	    @Column(name = "mobileNo", length = 10)
	    private String telephoneNo;
	    private String visitorCategory;
	    private int noOfVisitors;
	    private String purposeOfVisit;
	    private String toMeet;

	    private String hostName;
	    private String deptName;
	    private String locationDepartment;
	    private String unitNo;
	    private String permitNo;
	    private String deliveryOrder;
	    private String remarks;
	    //Additional Information
	    private boolean fever;
	    private boolean soreThroat;
	    private boolean dryCough;
	    private boolean runnyNose;
	    private boolean shortnessOfBreath;
	    private boolean bodyAche;
	    private boolean travelledOverseas;
	    private boolean contactWithCovid;
	    private boolean recoveredFromCovid;
	    private boolean covidTestDone;

	    private String gatePassNo;
	    private LocalDateTime visitingDateAndTime;

	    @PrePersist
	    public void generateUnitId() {
	        this.unitNo = "MCK/GP/25-26/" + String.format("%04d", this.id != null ? this.id : 1);
	    }	    
}
