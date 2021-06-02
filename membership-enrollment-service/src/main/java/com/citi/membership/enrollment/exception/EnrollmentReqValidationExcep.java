package com.citi.membership.enrollment.exception;
/**
*@authorAmeya
* Date Apr 21, 2021
*/
public class EnrollmentReqValidationExcep extends Exception{

	private String respCode;
	private String respMsg;

	public EnrollmentReqValidationExcep(String respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	public String getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	@Override
	public String toString() {
		return "EnrollmentReqValidationExcep [respCode=" + respCode + ", respMsg=" + respMsg + "]";
	}
	
}
