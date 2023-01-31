package com.jproject.my_cars;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MyCarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCarsApplication.class, args);
	}

}
