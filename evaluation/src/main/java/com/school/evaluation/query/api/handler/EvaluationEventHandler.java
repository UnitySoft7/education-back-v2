package com.school.evaluation.query.api.handler;

import com.school.evaluation.cmd.api.command.EvaluationCreatedCommand;
import com.school.evaluation.cmd.api.command.EvaluationUpdatedCommand;
import com.school.evaluation.core.model.Evaluation;
import reactor.core.publisher.Mono;

public
interface EvaluationEventHandler {
    Mono<Evaluation> create (EvaluationCreatedCommand command );

    Mono<Evaluation> update (EvaluationUpdatedCommand command);
}
