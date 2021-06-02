package com.citi.membership.enrollment.model;

import lombok.Data;

/**
*@authorAmeya
* Date Apr 22, 2021
*/

@Data
public class EnrollmentDAORequest {
	
	private String cardNum;
	private String channelId;
	private String cvv;
	private String enrollDate;
	private String expDate;
	private String nameOnCard;
	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String mobileNo;
	private String clientId;
	private String msgTs;
	
}
