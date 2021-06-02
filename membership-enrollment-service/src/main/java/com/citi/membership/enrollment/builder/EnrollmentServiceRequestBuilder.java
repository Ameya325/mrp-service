package com.citi.membership.enrollment.builder;

import com.citi.membership.enrollment.model.EnrollmentDAORequest;
import com.citi.membership.enrollment.model.EnrollmentRequest;

public class EnrollmentServiceRequestBuilder {

	public EnrollmentDAORequest  buildDAORequest(EnrollmentRequest enrollmentRequest) {
		EnrollmentDAORequest daoReq = new EnrollmentDAORequest();
		daoReq.setCardNum(enrollmentRequest.getCustomerInfo().getCardNum());
		daoReq.setCvv(enrollmentRequest.getCustomerInfo().getCvv());
		daoReq.setDob(enrollmentRequest.getCustomerInfo().getDob());
		daoReq.setEmail(enrollmentRequest.getCustomerInfo().getEmail());
		daoReq.setEnrollDate(enrollmentRequest.getCustomerInfo().getEnrollDate());
		daoReq.setExpDate(enrollmentRequest.getCustomerInfo().getExpDate());
		daoReq.setFirstName(enrollmentRequest.getCustomerInfo().getFirstName());
		daoReq.setLastName(enrollmentRequest.getCustomerInfo().getLastName());
		daoReq.setMobileNo(enrollmentRequest.getCustomerInfo().getMobileNo());
		daoReq.setNameOnCard(enrollmentRequest.getCustomerInfo().getNameOnCard());
		daoReq.setChannelId(enrollmentRequest.getClientInfo().getChannelId());
		daoReq.setClientId(enrollmentRequest.getClientInfo().getClientId());
		daoReq.setMsgTs(enrollmentRequest.getClientInfo().getMsgTs());

		return daoReq;
		
	}
}
