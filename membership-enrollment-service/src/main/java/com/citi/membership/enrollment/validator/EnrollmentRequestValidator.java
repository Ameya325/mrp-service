package com.citi.membership.enrollment.validator;

import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.exception.EnrollmentReqValidationExcep;
import com.citi.membership.enrollment.model.ClientInfo;
import com.citi.membership.enrollment.model.CustomerInfo;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.util.EnrollmentConstant;

/**
*@authorAmeya
* Date Apr 21, 2021
*/

@Component
public class EnrollmentRequestValidator {
	
	//TODO: Validate the request . If any one of the element is invalid send user defined exception.
	// Need to handle user defined exception.
	
	public void validate(final EnrollmentRequest enrollmentReq) throws EnrollmentReqValidationExcep {
		
		if(enrollmentReq == null || enrollmentReq.getClientInfo() == null || enrollmentReq.getCustomerInfo() == null) {
			throw new EnrollmentReqValidationExcep(EnrollmentConstant.ENS001, EnrollmentConstant.ENS001_DESC);
		}
		
		//client id null or empty scenarios
		final ClientInfo clientInfo = enrollmentReq.getClientInfo();
		
		//client id null or empty scenarios
		if ( clientInfo.getClientId() == null  || " ".equals(clientInfo.getClientId()) ) {
			throw new EnrollmentReqValidationExcep("ens002", "Invalid client id");
		}

		//channel id null or empty scenarios
		if ( clientInfo.getChannelId() == null  || " ".equals(clientInfo.getChannelId()) ) {
			throw new EnrollmentReqValidationExcep("ens003", "Invalid channel id");
		}

		//card number is empty or null scenarios
		final CustomerInfo customerInfo = enrollmentReq.getCustomerInfo();
		
		if ( customerInfo.getCardNum() == null  || " ".equals(customerInfo.getCardNum()) ) {
			throw new EnrollmentReqValidationExcep("ens004", "Invalid not available");
		}
	}
}
