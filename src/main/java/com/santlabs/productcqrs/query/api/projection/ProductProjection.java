package com.santlabs.productcqrs.query.api.projection;

import com.santlabs.productcqrs.command.api.data.Product;
import com.santlabs.productcqrs.command.api.dto.ProductDto;
import com.santlabs.productcqrs.command.api.repository.ProductRepository;
import com.santlabs.productcqrs.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductDto> handle(GetProductsQuery getProductsQuery){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList =
                products.stream()
                        .map(p -> ProductDto
                                .builder()
                                .name(p.getName())
                                .price(p.getPrice())
                                .qty(p.getQty())
                                .status(p.getStatus())
                                .build())
                        .collect(Collectors.toList());
        return productDtoList;
    }
}
