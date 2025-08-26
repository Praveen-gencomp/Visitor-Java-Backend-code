package VisitorDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VisitorResponseDTO {
	 private String identificationNo;
	    private String visitorName;
	    private String gender;
	    private String documentType;
	    private String licenseNumber;
	    private String address;
	    private String town;
	    private String postcode;
	    private String state;
	    private String country;
	    private String telephoneNo;
	    private String vehicleNumber;
	    private String vehicleType;
	    private String visitorCategory;
	    private String company;
	    private String preRegPassNo;
	    private String noOfVisitors;
	    private String purposeOfVisit;
	    private String toMeet;
	    private String hostInfo;
	    private String hostName;
	    private String deptName;
	    private String locationDepartment;
	    private String unitNo;
	    private String permitNo;
//	    private String deliveryOrder;
	    private String remarks;
	    private LocalDateTime visitingDateAndTime;

	    // Covid checks
	    private Boolean fever;
	    private Boolean soreThroat;
	    private Boolean dryCough;
	    private Boolean runnyNose;
	    private Boolean shortnessOfBreath;
	    private Boolean bodyAche;
	    private Boolean travelledOverseas;
	    private Boolean contactWithCovid;
	    private Boolean recoveredFromCovid;
	    private Boolean covidTestDone;

	    // Image
	    private String capturedImage;

}
