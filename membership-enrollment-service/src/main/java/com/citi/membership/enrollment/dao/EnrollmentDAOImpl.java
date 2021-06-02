package com.citi.membership.enrollment.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentDAORequest;
import com.citi.membership.enrollment.model.EnrollmentDAOResponse;

/**
 * @authorAmeya Date Apr 21, 2021
 */

@Component
public class EnrollmentDAOImpl implements EnrollmentDAO {

	private Logger logger = Logger.getLogger(EnrollmentDAOImpl.class);

	@Override
	public EnrollmentDAOResponse createEnroll(EnrollmentDAORequest daoReq) throws BusinessException, SystemException {
		logger.debug("EnrollmentDAOImpl.createEnroll()");
		System.out.println("Entered into EnrollmentDAOImpl");
		// 1. get the request from service
		// 2. prepare the request for database .i.e prepare the database queries

		String env = System.getProperty("environment");
		String fileName = "/properties/db/enrollment-" + env + "-db.properties";
		InputStream inStream = getClass().getClassLoader().getResourceAsStream(fileName);

		Properties p = new Properties();
		/*
		 * try { p.load(inStream); String dburl = System.getProperty("dburl"); String
		 * username = System.getProperty("username"); String password =
		 * System.getProperty("password"); } catch (IOException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */

		EnrollmentDAOResponse daoResp = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			String sql = "{call MRP_ENROLLMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = connection.prepareCall(sql);

			cs.registerOutParameter(13, Types.VARCHAR);
			cs.registerOutParameter(14, Types.VARCHAR);
			cs.registerOutParameter(15, Types.VARCHAR);

			cs.setString(1, daoReq.getClientId());
			cs.setString(2, "online");
			cs.setString(3, daoReq.getCardNum());
			cs.setString(4, daoReq.getCvv());
			cs.setString(5, daoReq.getExpDate());
			cs.setString(6, daoReq.getNameOnCard());
			cs.setString(7, daoReq.getFirstName());
			cs.setString(8, daoReq.getLastName());
			cs.setString(9, daoReq.getDob());
			cs.setString(10, daoReq.getEmail());
			cs.setString(11, daoReq.getMobileNo());
			cs.setString(12, daoReq.getEnrollDate());
			boolean b = cs.execute();

			String dbRespCode = cs.getString(13);
			String dbRespMsg = cs.getString(14);
			if ("000".equals(dbRespCode)) {
				// replace the hardcode values with database response
				daoResp = new EnrollmentDAOResponse();
				daoResp.setAcknNum(cs.getString(15));
				daoResp.setEnrollStatus("Enrollment Successful");
				daoResp.setResponseCode(dbRespCode);
				daoResp.setResponseMsg(dbRespMsg);
			} else if ("100".equals(dbRespCode) || "101".equals(dbRespCode) || "102".equals(dbRespCode)) {
				throw new BusinessException(dbRespCode, dbRespMsg);
			} else {
				throw new SystemException(dbRespCode, dbRespMsg);
			}
		} catch (BusinessException be) {
			be.printStackTrace();
//			logger.error("Business Exception in DAO", be);
			throw be;
		} catch (SystemException se) {
			se.printStackTrace();
//			logger.error("System Exception in DAO", se);
			throw se;
		} catch (Exception e) {
//			logger.fatal("Fatal Exception in DAO", e);
			e.printStackTrace();
			throw new SystemException("8888", "unknown error from database" + e.getMessage());
		}
//		logger.info("Exit from dao - end: : : :" + daoResp);
		System.out.println("Exit from dao - end: : : :" + daoResp);

		return daoResp;
	}

}
