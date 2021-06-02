package com.citi.membership.enrollment.controller;

/**
*@authorAmeya
* Date May 2, 2021
*/

import com.citi.membership.enrollment.model.ClientInfo;
import com.citi.membership.enrollment.model.CustomerInfo;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnrollmentToJsonObject {

	public static void main(String[] args) throws JsonProcessingException {
		EnrollmentRequest enrollmentRequest = new EnrollmentRequest();

		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setClientId("web");
		clientInfo.setChannelId("online");
		clientInfo.setMsgTs("abc123xyz");
		clientInfo.setReqId("27-04-2021");

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setFirstName("Raja");
		customerInfo.setLastName("Chaudhry");
		customerInfo.setDob("03-02-1995");
		customerInfo.setEmail("natarazraja@gmail.com");
		customerInfo.setExpDate("02-2024");
		customerInfo.setMobileNo("7894561325");
		customerInfo.setCvv("456");
		customerInfo.setCardNum("456789123456789");
		customerInfo.setNameOnCard("Natraz Chaudhry");
		customerInfo.setEnrollDate("20-02-2019");

		enrollmentRequest.setClientInfo(clientInfo);
		enrollmentRequest.setCustomerInfo(customerInfo);

		ObjectMapper mapper = new ObjectMapper();

		String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(enrollmentRequest);

		System.out.println(response);

	}

}
