package com.pandaabc.sesame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.pandaabc.sesame.jpa.entity")
@EnableJpaRepositories("com.pandaabc.sesame.jpa")
//@ComponentScan({"com.pandaabc.sesame.jpa.*","com.pandaabc.sesame.*"})
public class SesameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SesameApplication.class, args);
	}

}

