package com.santlabs.productcqrs.command.api.events;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductUpdatedEvent {

    private String productId;
    private BigDecimal price;
    private Integer qty;
    private String status;
    private String updateTs;

}
