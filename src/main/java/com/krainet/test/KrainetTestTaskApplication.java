package com.krainet.test;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(
				title = "Rest API test task",
				description = "Rest API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Ivan",
						email = "ivan.dauhalaptseu@gmail.com",
						url = "https://github.com/IvanDolg"
				),
				license = @License(
						name = "Krainet"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Rest API Documentation"
		)
)
@SpringBootApplication
public class KrainetTestTaskApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(KrainetTestTaskApplication.class, args);
	}

}
