package com.santlabs.productcqrs.command.api.commands;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class UpdateProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private BigDecimal price;
    private Integer qty;
    private String status;
    private String updateTs;
}
