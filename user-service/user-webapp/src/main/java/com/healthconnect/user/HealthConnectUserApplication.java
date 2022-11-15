package com.healthconnect.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.healthconnect.user.*")
@EnableJpaRepositories(basePackages = "com.healthconnect.user.*")
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class HealthConnectUserApplication extends SpringBootServletInitializer {

	private static Class applicationClass = HealthConnectUserApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(HealthConnectUserApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
}
