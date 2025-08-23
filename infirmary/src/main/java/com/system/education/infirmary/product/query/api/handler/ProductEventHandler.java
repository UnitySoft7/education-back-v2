package com.system.education.infirmary.product.query.api.handler;

import com.system.education.infirmary.product.cmd.api.command.ProductCreatedCommand;
import com.system.education.infirmary.product.cmd.api.command.ProductUpdatedCommand;
import com.system.education.infirmary.product.core.model.InfirmaryProduct;
import reactor.core.publisher.Mono;

public interface ProductEventHandler {
    Mono<InfirmaryProduct> create(ProductCreatedCommand command);

    Mono<InfirmaryProduct> update(ProductUpdatedCommand command);
}
