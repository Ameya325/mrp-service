package com.citi.membership.enrollment.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;
import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentDAORequest;
import com.citi.membership.enrollment.model.EnrollmentDAOResponse;

@Component
@Qualifier("enrollmentSpringDAOImpl ")
public class EnrollmentSpringDAOImpl extends StoredProcedure implements EnrollmentDAO{

	@Autowired
	public EnrollmentSpringDAOImpl (JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate,"MRP_ENROLLMENT");
		System.out.println("*****EnrollmentSpringDAOImpl*****");
		registerInputOutputParam();
	}
	
	private void registerInputOutputParam() {
		declareParameter(new SqlParameter("CLIENT_ID_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("CHANNEL_ID_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("CARD_NUM_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("CVV_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("EXPIRY_DATE_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("EXPIRY_DATE_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("NAME_ON_CARD_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("FIRST_NAME_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("LAST_NAME_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("DOB_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("EMAIL_ID_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("MOBILE_NUM_IN",Types.VARCHAR));
		declareParameter(new SqlParameter("ENROLLMENT_DATE_IN",Types.VARCHAR));
		
		declareParameter(new SqlOutParameter("RESP_CODE_OUT",Types.VARCHAR));
		declareParameter(new SqlOutParameter("RESP_MESSAGE_OUT",Types.VARCHAR));
		declareParameter(new SqlOutParameter("ACK_NUM_OUT",Types.VARCHAR));
		
		compile();
		
	}

	@Override
	public EnrollmentDAOResponse createEnroll(EnrollmentDAORequest daoReq) throws BusinessException, SystemException {
		
		EnrollmentDAOResponse daoResp = null;
		try {
			//prepare the stored procedure request
			Map<String,Object> requestMap = new HashMap<String, Object>();
			requestMap.put("CLIENT_ID_IN", daoReq.getClientId());
			requestMap.put("CHANNEL_ID_IN", daoReq.getChannelId());
			requestMap.put("CARD_NUM_IN", daoReq.getCardNum());
			requestMap.put("CVV_IN", daoReq.getCvv());
			requestMap.put("EXPIRY_DATE_IN", daoReq.getExpDate());
			requestMap.put("NAME_ON_CARD_IN", daoReq.getNameOnCard());
			requestMap.put("FIRST_NAME_IN", daoReq.getFirstName());
			requestMap.put("LAST_NAME_IN", daoReq.getLastName());
			requestMap.put("DOB_IN", daoReq.getDob());
			requestMap.put("EMAIL_ID_IN", daoReq.getEmail());
			requestMap.put("MOBILE_NUM_IN", daoReq.getMobileNo());
			requestMap.put("ENROLLMENT_DATE_IN", daoReq.getEnrollDate());
			
			Map<String,Object> respMap = super.execute(requestMap);
			String daoRespCode = (String) respMap.get("RESP_CODE_OUT");
			String daoRespMsg = (String) respMap.get("RESP_MESSAGE_OUT");
			
			daoResp = null;
			
			if ("000".equals(daoRespCode)) {
				// replace the hardcode values with database response
				daoResp = new EnrollmentDAOResponse();
				daoResp.setAcknNum((String) respMap.get("RESP_MESSAGE_OUT"));
				daoResp.setEnrollStatus("Enrollment Successful");
				daoResp.setResponseCode(daoRespCode);
				daoResp.setResponseMsg(daoRespMsg);
			} else if ("100".equals(daoRespCode) || "101".equals(daoRespCode) || "102".equals(daoRespCode)) {
				throw new BusinessException(daoRespCode, daoRespMsg);
			} else {
				throw new SystemException(daoRespCode, daoRespMsg);
			}
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("DAO response is:"+daoResp);
	return daoResp;
}
}
