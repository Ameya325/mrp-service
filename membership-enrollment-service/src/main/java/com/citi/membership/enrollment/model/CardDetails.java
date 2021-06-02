package com.citi.membership.enrollment.model;

import lombok.Data;

/**
*@authorAmeya
* Date May 20, 2021
*/

@Data
public class CardDetails {
	
	private String cardNum;
	private boolean primary;
	private boolean pastDue;
	private String productType;
	private String fname;
	private String lname;
	private String status;
	
}
