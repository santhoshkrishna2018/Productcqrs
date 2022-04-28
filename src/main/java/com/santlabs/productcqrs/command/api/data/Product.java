package com.santlabs.productcqrs.command.api.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer qty;
    private String status;
    private String createTs;
    private String updateTs;

}
