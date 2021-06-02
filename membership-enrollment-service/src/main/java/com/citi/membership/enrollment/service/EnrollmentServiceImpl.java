package com.citi.membership.enrollment.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.dao.EnrollmentDAO;
import com.citi.membership.enrollment.dao.EnrollmentDAOImpl;
import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.CardDetails;
import com.citi.membership.enrollment.model.CardDetailsResponse;
import com.citi.membership.enrollment.model.EnrollmentDAORequest;
import com.citi.membership.enrollment.model.EnrollmentDAOResponse;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.model.StatusBlock;
import com.citi.membership.enrollment.svcclient.CardServiceClient;
import com.citi.membership.enrollment.svcclient.CardServiceClientImpl;

/**
 * @authorAmeya Date Apr 21, 2021
 */

@Component
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	@Qualifier("enrollmentSpringDAOImpl")
	private EnrollmentDAO enrollmentDao;

	@Autowired
	private CardServiceClient cardServiceClient;

	private Logger logger = Logger.getLogger(EnrollmentServiceImpl.class);

	@Override
	public EnrollmentResponse createEnroll(EnrollmentRequest enrollmentReq) throws BusinessException, SystemException {
		logger.debug("Entered in service");
		// 1. Get the enrollment Request from controller

		// 2.Prepare the request for service client

		// 3. Call the service client

		cardServiceClient = new CardServiceClientImpl();
		CardDetailsResponse cardDetailsResponse = cardServiceClient
				.getCardDetails(enrollmentReq.getCustomerInfo().getCardNum());
		if (cardDetailsResponse != null) {
			List<CardDetails> cardDetailsList = cardDetailsResponse.getCardDetails();
			for (CardDetails cardDetails : cardDetailsList) {
				String card13Digit = cardDetails.getCardNum().substring(13, 15);
				if (!"pa".equals(cardDetails.getProductType()) || !"active".equals(cardDetails.getStatus())
						|| !cardDetails.isPrimary()) {
				}
			}
		}

		EnrollmentDAORequest daoReq = new EnrollmentDAORequest();
		daoReq.setCardNum(enrollmentReq.getCustomerInfo().getCardNum());
		daoReq.setCvv(enrollmentReq.getCustomerInfo().getCvv());
		daoReq.setDob(enrollmentReq.getCustomerInfo().getDob());
		daoReq.setEmail(enrollmentReq.getCustomerInfo().getEmail());
		daoReq.setEnrollDate(enrollmentReq.getCustomerInfo().getEnrollDate());
		daoReq.setExpDate(enrollmentReq.getCustomerInfo().getExpDate());
		daoReq.setFirstName(enrollmentReq.getCustomerInfo().getFirstName());
		daoReq.setLastName(enrollmentReq.getCustomerInfo().getLastName());
		daoReq.setMobileNo(enrollmentReq.getCustomerInfo().getMobileNo());
		daoReq.setNameOnCard(enrollmentReq.getCustomerInfo().getNameOnCard());

		daoReq.setClientId(enrollmentReq.getClientInfo().getClientId());
		daoReq.setMsgTs(enrollmentReq.getClientInfo().getMsgTs());

		// 3. Call dao and get the dao response

		enrollmentDao = new EnrollmentDAOImpl();

		EnrollmentDAOResponse daoResp = enrollmentDao.createEnroll(daoReq);

		// 4. Prepare service response with the help of dao

		EnrollmentResponse enrollResp = new EnrollmentResponse();

		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setResponseCode(daoResp.getResponseCode());
		statusBlock.setResponseMsg(daoResp.getResponseMsg());

		enrollResp.setStatusBlock(statusBlock);
		enrollResp.setEnrollStatus(daoResp.getEnrollStatus());
		enrollResp.setAcknNum(daoResp.getAcknNum());
		logger.info("Exit from service" + daoResp);

		return enrollResp;
	}

}
