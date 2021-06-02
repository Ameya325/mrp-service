package com.citi.membership.enrollment.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.EnrollmentReqValidationExcep;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.service.EnrollmentService;
import com.citi.membership.enrollment.service.EnrollmentServiceImpl;
import com.citi.membership.enrollment.validator.EnrollmentRequestValidator;

/**
*@authorAmeya
* Date Apr 21, 2021
*/

@RestController
@RequestMapping(value = "/customer")
public class EnrollmentController {
	
	private Logger logger = Logger.getLogger(EnrollmentController.class);
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private  EnrollmentRequestValidator validator;
	
	
	@RequestMapping(value = "/enrollment", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	@ResponseBody
	public EnrollmentResponse createEnroll(@RequestBody EnrollmentRequest enrollmentReq) throws EnrollmentReqValidationExcep, BusinessException, SystemException {
		
		logger.info("Entered into controller- start"+enrollmentReq);
		
		if (enrollmentReq.getClientInfo()!=null	 && enrollmentReq.getClientInfo().getReqId()!=null) {
			MDC.put("requestId",enrollmentReq.getClientInfo().getReqId());
		}

			//1. get the request from consumer/clients
			//2. validate the request
			validator = new EnrollmentRequestValidator();
			validator.validate(enrollmentReq);
			
			//3. prepare the request for service class
			//4. call service and get the response

			final EnrollmentResponse enrollmentResp = enrollmentService.createEnroll(enrollmentReq);
			
		
		//5. prepare the controller response
		
		logger.debug("Exit from controller - end");
		
		return enrollmentResp;
	}
	
	@RequestMapping(value = "/health" ,method = RequestMethod.GET)
	public String healthCheck() {
		logger.debug("EnrollmentController.healthCheck()");
		return "service is up and running";
	}
	
	
}
