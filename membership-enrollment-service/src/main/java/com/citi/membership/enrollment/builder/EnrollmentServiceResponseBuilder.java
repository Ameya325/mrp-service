package com.citi.membership.enrollment.builder;

import com.citi.membership.enrollment.model.EnrollmentDAORequest;
import com.citi.membership.enrollment.model.EnrollmentDAOResponse;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.model.StatusBlock;

public class EnrollmentServiceResponseBuilder {

	EnrollmentDAOResponse daoResp = new EnrollmentDAOResponse();
	StatusBlock statusBlock = new StatusBlock();

	public EnrollmentResponse buildEnrollmentResponse(EnrollmentResponse enrollResponse)
 {
	statusBlock.setResponseCode(daoResp.getResponseCode());
	statusBlock.setResponseMsg(daoResp.getResponseMsg());

	enrollResponse.setStatusBlock(statusBlock);
	enrollResponse.setEnrollStatus(daoResp.getEnrollStatus());
	enrollResponse.setAcknNum(daoResp.getAcknNum());
	return enrollResponse;
	}

}
