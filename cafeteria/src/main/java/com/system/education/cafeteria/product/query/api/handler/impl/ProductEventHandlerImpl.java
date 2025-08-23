package com.system.education.cafeteria.product.query.api.handler.impl;

import com.system.education.cafeteria.product.cmd.api.command.ProductCreatedCommand;
import com.system.education.cafeteria.product.cmd.api.command.ProductUpdatedCommand;
import com.system.education.cafeteria.product.core.model.Product;
import com.system.education.cafeteria.product.core.payload.ProductPayload;
import com.system.education.cafeteria.product.query.api.handler.ProductEventHandler;
import com.system.education.cafeteria.product.query.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductEventHandlerImpl implements ProductEventHandler {
    private final ProductRepository productRepository;
    private final ProductPayload productPayload;

    @Override
    public Mono<Product> create(ProductCreatedCommand command) {
        return productPayload.getCode()
                .flatMap(code -> {
                    Product product = Product.builder()
                            .productId(UUID.randomUUID().toString())
                            .productCode(code)
                            .productName(command.productName())
                            .amount(command.amount())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .build();
                    return productRepository.save(product);
                });

    }

    @Override
    public Mono<Product> update(ProductUpdatedCommand command) {
        return productRepository.findByProductCode(command.productCode())
                .flatMap(product -> {
                    product.setProductName(command.productName());
                    product.setAmount(command.amount());
                    return productRepository.save(product);
                });
    }
}
