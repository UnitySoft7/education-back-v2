package com.school.examination.query.api.handler;

import com.school.examination.cmd.api.command.ExaminationCreatedCommand;
import com.school.examination.cmd.api.command.ExaminationReclaimedCommand;
import com.school.examination.core.model.Examination;
import reactor.core.publisher.Mono;

public
interface ExaminationEventHandler {
    Mono<Examination> create (ExaminationCreatedCommand command );
    Mono<Examination> reclaim(ExaminationReclaimedCommand command);
}
