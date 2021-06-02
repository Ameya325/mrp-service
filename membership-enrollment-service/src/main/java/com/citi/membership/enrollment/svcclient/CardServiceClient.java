package com.citi.membership.enrollment.svcclient;

import com.citi.membership.enrollment.model.CardDetailsResponse;

/**
*@authorAmeya
* Date May 5, 2021
*/
public interface CardServiceClient {
	
	CardDetailsResponse getCardDetails(String cardNum);
	

}
