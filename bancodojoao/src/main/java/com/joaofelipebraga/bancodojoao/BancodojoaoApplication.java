package com.joaofelipebraga.bancodojoao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BancodojoaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancodojoaoApplication.class, args);
	}

}
