package com.plans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI apiDoc() {
		return new OpenAPI()
				.info(new Info()
						.title("Plans API")
						.description("Insurance Project + OpenAPI")
						.version("1.0"));
	}

}