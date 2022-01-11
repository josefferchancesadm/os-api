package com.apps.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.apps.os.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {


	@Autowired
	private DBService dbservice;
	
	@Bean
	public void instaciaDB() {

		this.dbservice.instaciaDB();

	}

}
