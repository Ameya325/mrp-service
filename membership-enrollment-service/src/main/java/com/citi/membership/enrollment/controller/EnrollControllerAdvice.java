package com.citi.membership.enrollment.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.EnrollmentReqValidationExcep;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.model.StatusBlock;
import com.citi.membership.enrollment.util.EnrollmentConstant;

/**
 * @authorAmeya Date May 3, 2021
 */

@ControllerAdvice
public class EnrollControllerAdvice {

	private Logger logger = Logger.getLogger(EnrollControllerAdvice.class);
	
	@ExceptionHandler(value = EnrollmentReqValidationExcep.class)
	@ResponseBody
	public EnrollmentResponse handleRequestInvalidException(EnrollmentReqValidationExcep excep) {
		logger.error("EnrollmentReqValidationException from	 Validator",excep);
		EnrollmentResponse enrollResp = buildStatusBlock(excep.getRespCode(), excep.getRespMsg());
		return enrollResp;
	}

	@ExceptionHandler(value = SystemException.class)
	@ResponseBody
	public EnrollmentResponse handleSystemError(SystemException excep) {
		logger.error("SystemException from	 Validator",excep);
		EnrollmentResponse enrollResp = buildStatusBlock(excep.getRespCode(), excep.getRespMsg());
		return enrollResp;
	}
	
	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public EnrollmentResponse handleDataError(BusinessException excep) {
		logger.error("BusinessException from	 Validator",excep);
		EnrollmentResponse enrollResp = buildStatusBlock(excep.getRespCode(), excep.getRespMsg());
		return enrollResp;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public EnrollmentResponse handleDataError(Exception excep) {
		logger.error("Exception from EnrollControlAdvice",excep);
		EnrollmentResponse enrollResp = buildStatusBlock(EnrollmentConstant.GENERIC_ERROR_CODE,EnrollmentConstant.GENERIC_ERROR_MSG);
		return enrollResp;
	}
	
	private EnrollmentResponse buildStatusBlock(String respCode, String respMsg) {
		EnrollmentResponse enrollResp = new EnrollmentResponse();
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setResponseCode(respCode);
		statusBlock.setResponseMsg(respMsg);
		enrollResp.setStatusBlock(statusBlock);
		return enrollResp;
	}
}
