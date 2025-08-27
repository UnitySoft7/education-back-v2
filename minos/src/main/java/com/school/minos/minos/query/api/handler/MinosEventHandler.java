package com.school.minos.minos.query.api.handler;

import com.school.minos.minos.cmd.api.command.MinosPayedCommand;
import com.school.minos.transaction.core.model.Transaction;
import reactor.core.publisher.Mono;

public
interface MinosEventHandler {
    Mono<Transaction> pay(MinosPayedCommand command);
}
