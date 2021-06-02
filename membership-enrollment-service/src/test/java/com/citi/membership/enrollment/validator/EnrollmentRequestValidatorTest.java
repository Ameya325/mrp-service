package com.citi.membership.enrollment.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.citi.membership.enrollment.exception.EnrollmentReqValidationExcep;
import com.citi.membership.enrollment.model.ClientInfo;
import com.citi.membership.enrollment.model.CustomerInfo;
import com.citi.membership.enrollment.model.EnrollmentRequest;

/**
*@authorAmeya
* Date May 5, 2021
*/
public class EnrollmentRequestValidatorTest {
	
	EnrollmentRequest enrollmentRequest = null;
	EnrollmentRequestValidator validator = null;
	
	@Before
	public void setUp() throws Exception {
		enrollmentRequest = buildEnrollmentRequest();
		validator = new EnrollmentRequestValidator();
	}

	@Test
	public void testValidateEnrollmentReqNullScenarios() {
		try {
			validator.validate(enrollmentRequest);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens001", e.getRespCode());
		}
	}
	@Test
	public void testValidateClientInfoNullScenarios() {
		try {
			enrollmentRequest.setClientInfo(null);
			validator.validate(enrollmentRequest);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens001", e.getRespCode());
		}
	}
	
	@Test
	public void testValidateCustomerInfoNullScenarios() {
		try {
			enrollmentRequest.setCustomerInfo(null);
			validator.validate(enrollmentRequest);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens001", e.getRespCode());
		}
	}
	
	@Test
	public void testValidateClientIdNullScenarios() {
		try {
			enrollmentRequest.getClientInfo().setClientId(null);
			validator.validate(enrollmentRequest);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens002", e.getRespCode());
		}
	}
	

	@After
	public void tearDown() throws Exception {
		validator = null;
		enrollmentRequest = null;
	}

	@Test
	public void testValidateChannelIdNullScenarios() {
		try {
			enrollmentRequest.getClientInfo().setChannelId(null);
			validator.validate(enrollmentRequest);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens003", e.getRespCode());
		}
	}
	
	@Test
	public void testValidateCardNumNullScenarios() {
		try {
			enrollmentRequest.getCustomerInfo().setCardNum(null);
			validator.validate(enrollmentRequest);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens004", e.getRespCode());
		}
	}
	
	private EnrollmentRequest buildEnrollmentRequest() {
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
		
		return enrollmentRequest;
	}
}
