package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("Feign-Customer-Service-9092", r -> r.path("/customers/**").uri("lb://Feign-Customer-Service-9092"))
				.route("Feign-Product-Service-9091", r -> r.path("/products/**").uri("lb://Feign-Product-Service-9091"))
				.route("Feign-Order-Service-9093", r -> r.path("/orders/**").uri("lb://Feign-Order-Service-9093"))
				.build();
	}
	
}