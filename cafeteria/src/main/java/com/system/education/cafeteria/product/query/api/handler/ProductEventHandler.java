package com.system.education.cafeteria.product.query.api.handler;

import com.system.education.cafeteria.product.cmd.api.command.ProductCreatedCommand;
import com.system.education.cafeteria.product.cmd.api.command.ProductUpdatedCommand;
import com.system.education.cafeteria.product.core.model.Product;
import reactor.core.publisher.Mono;

public interface ProductEventHandler {
    Mono<Product> create(ProductCreatedCommand command);

    Mono<Product> update(ProductUpdatedCommand command);
}
