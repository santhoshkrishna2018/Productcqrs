package com.santlabs.productcqrs.command.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class UpdateProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private BigDecimal price;
    private Integer qty;
    private String status;
}
