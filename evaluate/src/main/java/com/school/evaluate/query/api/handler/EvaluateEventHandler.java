package com.school.evaluate.query.api.handler;

import com.school.evaluate.cmd.api.command.EvaluateCreatedCommand;
import com.school.evaluate.cmd.api.command.EvaluateReclaimedCommand;
import com.school.evaluate.cmd.api.command.EvaluateUpdatedCommand;
import com.school.evaluate.core.model.Evaluate;
import reactor.core.publisher.Mono;

public
interface EvaluateEventHandler {
    Mono<Evaluate> create ( EvaluateCreatedCommand command );

    Mono<Evaluate> update (EvaluateUpdatedCommand command);

    Mono<Evaluate> reclaim(EvaluateReclaimedCommand command);
}
