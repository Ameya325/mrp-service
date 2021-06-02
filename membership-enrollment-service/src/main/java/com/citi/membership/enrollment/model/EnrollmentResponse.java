package com.citi.membership.enrollment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
*@authorAmeya
* Date Apr 21, 2021
*/

@Data
public class EnrollmentResponse {

	private StatusBlock statusBlock;
	
	@JsonInclude(value = Include.NON_NULL)
	private String enrollStatus;
	
	@JsonIgnore
	private String description;
	
	@JsonInclude(value = Include.NON_NULL)
	private String acknNum;
}
