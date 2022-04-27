package com.santlabs.productcqrs.query.api.controller;

import com.santlabs.productcqrs.command.api.dto.ProductDto;
import com.santlabs.productcqrs.query.api.queries.GetProductsQuery;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    private final EventStore eventStore;

    public ProductQueryController(QueryGateway queryGateway, EventStore eventStore) {
        this.queryGateway = queryGateway;
        this.eventStore = eventStore;
    }
    @GetMapping
    public List<ProductDto> getProducts(){

        GetProductsQuery getProductsQuery = new GetProductsQuery();
        List<ProductDto> productDtoList = queryGateway.query(getProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductDto.class))
                .join();
        return  productDtoList;
    }

    @GetMapping("/events/{productId}")
    public List<Object> listEventsForProduct(@PathVariable String productId){
        return eventStore.readEvents(productId).asStream().map(p -> p.getPayload()).collect(Collectors.toList());
    }
}
