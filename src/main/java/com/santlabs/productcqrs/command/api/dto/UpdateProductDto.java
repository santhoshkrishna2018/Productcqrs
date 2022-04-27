package com.santlabs.productcqrs.command.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class UpdateProductDto {
    private BigDecimal price;
    private Integer qty;
}
