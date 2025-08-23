package com.school.minos.tariff.query.api.handler;

import com.school.minos.tariff.cmd.api.command.TariffCreatedCommand;
import com.school.minos.tariff.core.model.Tariff;
import reactor.core.publisher.Mono;

public
interface TariffEventHandler {
    Mono<Tariff> create ( TariffCreatedCommand command );
}
