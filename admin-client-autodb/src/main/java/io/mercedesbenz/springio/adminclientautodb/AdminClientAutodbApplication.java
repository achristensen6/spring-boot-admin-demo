package io.mercedesbenz.springio.adminclientautodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AdminClientAutodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminClientAutodbApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propsConfig
				= new PropertySourcesPlaceholderConfigurer();
		propsConfig.setLocation(new ClassPathResource("git.properties"));
		propsConfig.setIgnoreResourceNotFound(true);
		propsConfig.setIgnoreUnresolvablePlaceholders(true);
		return propsConfig;
	}
}
