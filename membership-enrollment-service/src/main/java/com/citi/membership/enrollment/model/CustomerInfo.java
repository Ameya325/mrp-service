package com.citi.membership.enrollment.model;

import lombok.Data;

/**
*@authorAmeya
* Date Apr 22, 2021
*/

@Data
public class CustomerInfo {
	
	private String cardNum;
	private String cvv;
	private String expDate;
	private String nameOnCard;
	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String mobileNo;
	private String enrollDate;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerInfo [nameOnCard=");
		builder.append(nameOnCard);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", email=");
		builder.append(email);
		builder.append(", mobileNo=");
		builder.append(mobileNo);
		builder.append(", enrollDate=");
		builder.append(enrollDate);
		builder.append("]");
		return builder.toString();
	}
}
	