package com.santlabs.productcqrs.command.api.events;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCreatedEvent {

    private String productId;
    private String name;
    private BigDecimal price;
    private Integer qty;
    private String status;
}
