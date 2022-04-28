package com.santlabs.productcqrs.command.api.aggregate;

import com.santlabs.productcqrs.command.api.commands.CreateProductCommand;
import com.santlabs.productcqrs.command.api.commands.UpdateProductCommand;
import com.santlabs.productcqrs.command.api.events.ProductCreatedEvent;
import com.santlabs.productcqrs.command.api.events.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;


@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer qty;
    private String status;
    private String createTs;
    private String updateTs;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        //Do all the validations related to createProductCommand (received data to api)

        ProductCreatedEvent productCreatedEvent =
                new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);
        System.out.println("productCreatedEvent :"+productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    public ProductAggregate() {
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();
        this.qty = productCreatedEvent.getQty();
        this.status =productCreatedEvent.getStatus();
        this.createTs = productCreatedEvent.getCreateTs();
        this.updateTs = productCreatedEvent.getUpdateTs();
    }

    @CommandHandler
    public void on(UpdateProductCommand updateProductCommand){
        System.out.println("updateProductCommand ="+updateProductCommand);
        ProductUpdatedEvent updatedEvent = new ProductUpdatedEvent();
        BeanUtils.copyProperties(updateProductCommand,updatedEvent);
        System.out.println("updatedEvent :"+updatedEvent);
        AggregateLifecycle.apply(updatedEvent);

    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent updatedEvent){
        this.productId = updatedEvent.getProductId();
        this.price = updatedEvent.getPrice();
        this.qty = updatedEvent.getQty();
        this.status =updatedEvent.getStatus();
        this.updateTs = updatedEvent.getUpdateTs();
    }

}
