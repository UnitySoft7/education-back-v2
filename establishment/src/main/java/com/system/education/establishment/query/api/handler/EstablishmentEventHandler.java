package com.system.education.establishment.query.api.handler;

import com.system.education.establishment.cmd.api.command.ChangeEstablishmentStateCommand;
import com.system.education.establishment.cmd.api.command.EstablishmentCreatedCommand;
import com.system.education.establishment.cmd.api.command.EstablishmentUpdatedCommand;
import com.system.education.establishment.core.model.Establishment;
import reactor.core.publisher.Mono;

public interface EstablishmentEventHandler {
    Mono<Establishment> create(EstablishmentCreatedCommand command);

    Mono<Establishment> enable(ChangeEstablishmentStateCommand command);

    Mono<Establishment> disable(ChangeEstablishmentStateCommand command);

    Mono<Establishment> update(EstablishmentUpdatedCommand command);
}
