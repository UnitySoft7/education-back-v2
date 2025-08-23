package com.system.education.infirmary.diagnosis.query.api.handler.impl;

import com.system.education.infirmary.core.common.LogCreated;
import com.system.education.infirmary.diagnosis.cmd.api.command.DiagnosisCreatedCommand;
import com.system.education.infirmary.diagnosis.core.model.Diagnosis;
import com.system.education.infirmary.diagnosis.core.payload.DiagnosisPayload;
import com.system.education.infirmary.diagnosis.query.api.handler.DiagnosisEventHandler;
import com.system.education.infirmary.diagnosis.query.api.repository.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DiagnosisEventHandlerImpl implements DiagnosisEventHandler {
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisPayload diagnosisPayload;

    @Override
    public Mono<Diagnosis> create(DiagnosisCreatedCommand command) {

        return diagnosisPayload.getCode()
                .flatMap(code -> {
                    Diagnosis diagnosis = Diagnosis.builder()
                            .diagnosisId(UUID.randomUUID().toString())
                            .diagnosisCode(code)
                            .studentCode(command.studentCode())
                            .studentName(command.studentName())
                            .condition(command.condition())
                            .comment(command.comment())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .semester(command.semester())
                            .schoolYear(command.schoolYear())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();
                    return diagnosisRepository.save(diagnosis);
                });
    }
}
