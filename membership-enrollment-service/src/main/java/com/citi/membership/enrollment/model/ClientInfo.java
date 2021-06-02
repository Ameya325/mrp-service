package com.citi.membership.enrollment.model;

import lombok.Data;

/**
*@authorAmeya
* Date Apr 22, 2021
*/

@Data
public class ClientInfo {
	
	private String clientId;
	private String channelId;
	private String reqId;
	private String msgTs;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientInfo [clientId=");
		builder.append(clientId);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", reqId=");
		builder.append(reqId);
		builder.append(", msgTs=");
		builder.append(msgTs);
		builder.append("]");
		return builder.toString();
	}
	
}
