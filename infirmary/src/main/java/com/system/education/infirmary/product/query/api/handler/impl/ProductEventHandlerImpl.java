package com.system.education.infirmary.product.query.api.handler.impl;

import com.system.education.infirmary.product.cmd.api.command.ProductCreatedCommand;
import com.system.education.infirmary.product.cmd.api.command.ProductUpdatedCommand;
import com.system.education.infirmary.product.core.model.InfirmaryProduct;
import com.system.education.infirmary.product.core.payload.ProductPayload;
import com.system.education.infirmary.product.query.api.handler.ProductEventHandler;
import com.system.education.infirmary.product.query.api.repository.ProductRepository;
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
    public Mono<InfirmaryProduct> create(ProductCreatedCommand command) {
        return productPayload.getCode()
                .flatMap(code -> {
                    InfirmaryProduct infirmaryProduct = InfirmaryProduct.builder()
                            .productId(UUID.randomUUID().toString())
                            .productCode(code)
                            .productName(command.productName())
                            .amount(command.amount())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .build();
                    return productRepository.save(infirmaryProduct);
                });

    }

    @Override
    public Mono<InfirmaryProduct> update(ProductUpdatedCommand command) {
        return productRepository.findByProductCode(command.productCode())
                .flatMap(product -> {
                    product.setProductName(command.productName());
                    product.setAmount(command.amount());
                    return productRepository.save(product);
                });
    }
}
