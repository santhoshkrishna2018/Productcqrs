package com.santlabs.productcqrs.command.api.events;

import com.santlabs.productcqrs.command.api.data.Product;
import com.santlabs.productcqrs.command.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        System.out.println(productCreatedEvent);
        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent,product);
        productRepository.save(product);
        //If we get any exceptions in this, ExceptionHandler will take care of it.
    }
    @ExceptionHandler
    public void handle(Exception e) throws Exception {
        throw e;
    }
    @EventHandler
    public void on(ProductUpdatedEvent productupdatedEvent){
        Product product = productRepository.getById(productupdatedEvent.getProductId());
        product.setPrice(productupdatedEvent.getPrice());
        product.setQty(productupdatedEvent.getQty());
        product.setStatus(productupdatedEvent.getStatus());
        productRepository.save(product);
        //If we get any exceptions in this, ExceptionHandler will take care of it.
    }
}
