package com.citi.membership.enrollment.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

public class CallableTest {
	private static final Logger logger = Logger.getLogger(CallableTest.class);

	public static void main(final String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		String sql = "{call MRP_ENROLLMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		CallableStatement cs = connection.prepareCall(sql);

		cs.registerOutParameter(13, Types.VARCHAR);
		cs.registerOutParameter(14, Types.VARCHAR);
		cs.registerOutParameter(15, Types.VARCHAR);

		cs.setString(1, "web");
		cs.setString(2, "online");
		cs.setString(3, "44444444444");
		cs.setLong(4, 123);
		cs.setString(5, "12-2018");
		cs.setString(6, "sunny");
		cs.setString(7, "sreeenu");
		cs.setString(8, "tech");
		cs.setString(9, "24-03-2018");
		cs.setString(10, "sreenu.sreenutech@gmail.com");
		cs.setString(11, "987878978");
		cs.setString(12, "30-12-2018");
		boolean b = cs.execute();

		System.out.println(" boolean value is :" + b);

		System.out.println(cs.getString(13));
		System.out.println(cs.getString(14));
		System.out.println(cs.getString(15));
	}
}
