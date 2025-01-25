package com.customer.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

	@Bean
	ModelMapper modelMapperBean() {
		return new ModelMapper();
	}
}
