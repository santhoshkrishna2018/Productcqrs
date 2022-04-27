package com.santlabs.productcqrs;

import com.santlabs.productcqrs.exception.ProductServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductscqrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductscqrsApplication.class, args);
	}

	public void configure(EventProcessingConfigurer configurer){
		configurer.registerListenerInvocationErrorHandler("product",
				configuration -> new ProductServiceEventsErrorHandler()
		);
	}
}
