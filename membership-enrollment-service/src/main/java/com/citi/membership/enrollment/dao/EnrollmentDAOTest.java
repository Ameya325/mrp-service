package com.citi.membership.enrollment.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

public class EnrollmentDAOTest {
	private static final Logger logger = Logger.getLogger(EnrollmentDAOTest.class);

	public static void main(final String[] args) throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			final String sql = "{call MRP_ENROLLMENTS_001(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

			logger.info("Connection is:" + connection);

			// prepare the stored procedure params
			final CallableStatement cs = connection.prepareCall(sql);

			logger.info("callable statement is:" + cs);

			// prepare the stored procedures request
			cs.setString(1, "web");
			cs.setString(2, "online");
			cs.setString(3, "0521140058239101");
			cs.setString(4, "'123");
			cs.setString(5, "12-2021");
			cs.setString(6, "sreenu");
			cs.setString(7, "sreenu");
			cs.setString(8, "tech");
			cs.setString(9, "24-08-2020");
			cs.setString(10, "sreenu.sreenutech@gmail.com");
			cs.setString(11, "3434343434");
			cs.setString(12, "13-05-2021");

//		cs.registerOutParameter(13, Types.VARCHAR);
			cs.registerOutParameter("RESP_CODE_OUT", 13);
			cs.registerOutParameter(14, Types.VARCHAR);
			cs.registerOutParameter(15, Types.VARCHAR);

//			final boolean b = cs.execute();

//		final ResultSet rs = cs.executeQuery();
//		logger.info(cs.getString(13));
//		logger.info(cs.getString(14));
//		logger.info(cs.getString(15));
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}
}
