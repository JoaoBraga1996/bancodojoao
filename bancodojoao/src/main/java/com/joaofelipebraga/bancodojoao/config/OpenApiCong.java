package com.joaofelipebraga.bancodojoao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiCong {

	@Bean
	OpenAPI customOpenApi() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("Banco Do João").description("Esse é o Banco Digital da EDUC360"));

	}

}
