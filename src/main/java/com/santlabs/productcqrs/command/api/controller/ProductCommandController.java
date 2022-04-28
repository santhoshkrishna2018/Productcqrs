package com.santlabs.productcqrs.command.api.controller;

import com.santlabs.productcqrs.command.api.commands.CreateProductCommand;
import com.santlabs.productcqrs.command.api.commands.UpdateProductCommand;
import com.santlabs.productcqrs.command.api.dto.ProductDto;
import com.santlabs.productcqrs.command.api.dto.UpdateProductDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductDto productDto){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTs = dateFormat.format(date);
        System.out.println("createTs :"+createTs);
        CreateProductCommand createProductCommand =
                CreateProductCommand.builder()
                        .productId(UUID.randomUUID().toString())
                        .name(productDto.getName())
                        .price(productDto.getPrice())
                        .qty(productDto.getQty())
                        .status("Draft created")
                        .createTs(createTs)
                        .updateTs(createTs)
                        .build();
        System.out.println("createProductCommand = "+createProductCommand);
        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable String productId, @RequestBody UpdateProductDto updateProductDto){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String updateTs = dateFormat.format(date);
        System.out.println("productId :"+productId);
        UpdateProductCommand updateProductCommand =
                UpdateProductCommand.builder()
                        .productId(productId)
                        .qty(updateProductDto.getQty())
                        .price(updateProductDto.getPrice())
                        .status("Product Updated")
                        .updateTs(updateTs)
                        .build();
        System.out.println("updateProductCommand :"+updateProductCommand);
        String result = commandGateway.sendAndWait(updateProductCommand);
        System.out.println("result ="+result);
        return result;
    }
}
