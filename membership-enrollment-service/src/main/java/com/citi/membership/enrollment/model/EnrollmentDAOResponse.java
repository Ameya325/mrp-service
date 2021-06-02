package com.citi.membership.enrollment.model;

import lombok.Data;

/**
*@authorAmeya
* Date Apr 22, 2021
*/

@Data
public class EnrollmentDAOResponse {
	
	private String acknNum;
	private String EnrollStatus;
	private String responseCode;
	private String responseMsg;
	
}
