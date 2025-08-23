package com.school.minos.query.api.handler;

import com.school.minos.cmd.api.command.MinosCreatedCommand;
import com.school.minos.cmd.api.command.MinosPayedCommand;
import com.school.minos.cmd.api.command.MinosUpdatedCommand;
import com.school.minos.core.model.Minos;
import com.school.minos.transaction.core.model.Transaction;
import reactor.core.publisher.Mono;

public
interface MinosEventHandler {
    Mono<Minos> create ( MinosCreatedCommand command );

    Mono<Transaction> pay(MinosPayedCommand command);

    Mono<Minos> update(MinosUpdatedCommand command);
}
