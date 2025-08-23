package com.system.education.infirmary.consumed.query.api.handler;

import com.system.education.infirmary.consumed.cmd.api.command.ConsumedCreatedCommand;
import com.system.education.infirmary.consumed.cmd.api.command.PayAllProductCommand;
import com.system.education.infirmary.consumed.core.model.InfirmaryConsumed;
import com.system.education.infirmary.core.dto.MessageResponse;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
import reactor.core.publisher.Mono;

public interface ConsumedEventHandler {
    Mono<MessageResponse> create(ConsumedCreatedCommand command);

    Mono<InfirmaryConsumed> payProduct(ProductByIdQuery command);

    Mono<MessageResponse> payAllProduct(PayAllProductCommand command);

//    Mono<Consumed> update(ConsumedUpdatedCommand command);
}
