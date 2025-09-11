package com.project.IMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.project.IMS.entity")
@ComponentScan(basePackages = "com.project.IMS")
@EnableJpaRepositories(basePackages = "com.project.IMS.repository")
public class InventryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventryManagementSystemApplication.class, args);
	}

}
