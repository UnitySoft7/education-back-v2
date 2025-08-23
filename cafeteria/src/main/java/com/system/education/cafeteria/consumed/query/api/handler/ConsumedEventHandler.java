package com.system.education.cafeteria.consumed.query.api.handler;

import com.system.education.cafeteria.consumed.cmd.api.command.ConsumedCreatedCommand;
import com.system.education.cafeteria.consumed.cmd.api.command.PayAllProductCommand;
import com.system.education.cafeteria.consumed.core.model.Consumed;
import com.system.education.cafeteria.core.dto.MessageResponse;
import com.system.education.cafeteria.product.query.api.query.ProductByIdQuery;
import reactor.core.publisher.Mono;

public interface ConsumedEventHandler {
    Mono<MessageResponse> create(ConsumedCreatedCommand command);

    Mono<Consumed> payProduct(ProductByIdQuery command);

    Mono<MessageResponse> payAllProduct(PayAllProductCommand command);

//    Mono<Consumed> update(ConsumedUpdatedCommand command);
}
