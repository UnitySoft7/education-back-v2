package com.system.education.infirmary.diagnosis.query.api.handler;

import com.system.education.infirmary.diagnosis.cmd.api.command.DiagnosisCreatedCommand;
import com.system.education.infirmary.diagnosis.core.model.Diagnosis;
import reactor.core.publisher.Mono;

public interface DiagnosisEventHandler {
    Mono<Diagnosis> create(DiagnosisCreatedCommand command);
}
