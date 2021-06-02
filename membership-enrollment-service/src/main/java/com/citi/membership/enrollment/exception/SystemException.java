package com.citi.membership.enrollment.exception;
/**
*@authorAmeya
* Date May 3, 2021
*/
public class SystemException extends Exception{

	private String respCode;
	private String respMsg;
	
	public SystemException(String respCode, String respMsg) {
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
		return "SystemException [respCode=" + respCode + ", respMsg=" + respMsg + "]";
	}
	
}
