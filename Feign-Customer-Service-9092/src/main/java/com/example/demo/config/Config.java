package com.example.demo.config;

import java.util.Map;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public InfoContributor appInfoContributor() {
		InfoContributor info = builder -> {
			builder.withDetail("app", Map.of(
					"name", "Feign-Customer-Service App", 
					"description", "Feign-Customer-Service-9092", 
					"version", "1.0.0",
					"author", "Tingli", 
					"last_updated", "2024-06-07"));
		};

		return info;

	}
}
