package com.citi.membership.enrollment.svcclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import javax.smartcardio.Card;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.citi.membership.enrollment.model.CardDetailsResponse;

/**
 * @authorAmeya Date May 5, 2021
 */

@Component
public class CardServiceClientImpl implements CardServiceClient {
	
	@Value("{service-url}")
	private String serviceUrl;
	
	@Value("{service-url}")
	private String serviceTimeout;
	
	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = Logger.getLogger(CardServiceClientImpl.class);
	
	@Override
	public CardDetailsResponse getCardDetails(String cardNum) {
		CardDetailsResponse svcResponse = null;

		try {
			String env = System.getProperty("environment");
			logger.info(env);
			String fileName = "properties/service/card-details-service-"+env+".properties";
			logger.info(fileName);
			InputStream inStream = getClass().getClassLoader().getResourceAsStream(fileName);
			System.out.println("serviceUrl:"+serviceUrl);

//			Properties p = new Properties();
//			p.load(inStream);
//			String serviceUrl = p.getProperty("service-url");
//			String timeout = p.getProperty("service-timeout");
			logger.info("service-url:"+serviceUrl);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");
			headers.set("clientId", "mrp");
			headers.set("requestId", UUID.randomUUID().toString().substring(0, 16));
			headers.set("msgTs", "24-04-2021");
		} catch (RestClientException e) {
			logger.error("RestClientException ",e);
		}

		logger.info("response:" + svcResponse);
		return svcResponse;
	}

	/*public static void main(String[] args) {
		System.setProperty("environment", "test");
		CardServiceClientImpl svcClient = new CardServiceClientImpl();
		CardDetailsResponse cardDetailsResponse = svcClient.getCardDetails("0521140058239101");
		System.out.println("Response is:" + cardDetailsResponse);

	}*/
}
