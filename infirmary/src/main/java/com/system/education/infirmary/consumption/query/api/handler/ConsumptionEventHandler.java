package com.system.education.infirmary.consumption.query.api.handler;

import com.system.education.infirmary.consumption.cmd.api.command.ConsumptionCreatedCommand;
import com.system.education.infirmary.consumption.cmd.api.command.ConsumptionPayCommand;
import com.system.education.infirmary.core.dto.MessageResponse;
import reactor.core.publisher.Mono;

public interface ConsumptionEventHandler {
    Mono<MessageResponse> create(ConsumptionCreatedCommand command);

    Mono<MessageResponse> pay(ConsumptionPayCommand command);
}
