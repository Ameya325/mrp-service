package com.citi.membership.enrollment.dao;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentDAORequest;
import com.citi.membership.enrollment.model.EnrollmentDAOResponse;

/**
*@authorAmeya
* Date Apr 21, 2021
*/


public interface EnrollmentDAO {
	
	public EnrollmentDAOResponse createEnroll(EnrollmentDAORequest daoReq) throws BusinessException, SystemException;

}
