package com.citi.membership.enrollment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
*@authorAmeya
* Date Apr 30, 2021
*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.citi.membership.enrollment")
public class EnrollmentConfiguration {

		@Bean
		@Profile("dev")
		public PropertySourcesPlaceholderConfigurer devEnvProperties() {
			System.out.println("load dev env properties");
			PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
			placeHolder.setLocation(new ClassPathResource("properties/service/card-details-service-dev.properties"));
			return placeHolder;
		}
		
		@Bean
		@Profile("test")
		public PropertySourcesPlaceholderConfigurer testEnvProperties() {
			System.out.println("load test env properties");
			PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
			placeHolder.setLocation(new ClassPathResource("properties/service/card-details-service-test.properties"));
			return placeHolder;
		}
		
		@Bean
		@Profile("uat")
		public PropertySourcesPlaceholderConfigurer uatEnvProperties() {
			System.out.println("load uat env properties");
			PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
			placeHolder.setLocation(new ClassPathResource("properties/service/card-details-service-uat.properties"));
			return placeHolder;
		}

		@Bean
		@Profile("prod")
		public PropertySourcesPlaceholderConfigurer prodEnvProperties() {
			System.out.println("load prod env properties");
			PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
			placeHolder.setLocation(new ClassPathResource("properties/service/card-details-service-prod.properties"));
			return placeHolder;
		}
		
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
		
		@Bean
		public JdbcTemplate jdbcTemplate() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/test");
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			return new JdbcTemplate(dataSource);
		}
		 
		
		
		
		
		
		
}