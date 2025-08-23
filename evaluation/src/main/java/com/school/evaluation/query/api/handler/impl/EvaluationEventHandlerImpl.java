package com.school.evaluation.query.api.handler.impl;

import com.school.evaluation.cmd.api.command.EvaluationCreatedCommand;
import com.school.evaluation.cmd.api.command.EvaluationUpdatedCommand;
import com.school.evaluation.core.common.LogCreated;
import com.school.evaluation.core.model.Evaluation;
import com.school.evaluation.core.payload.EvaluationPayload;
import com.school.evaluation.query.api.handler.EvaluationEventHandler;
import com.school.evaluation.query.api.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EvaluationEventHandlerImpl implements EvaluationEventHandler {
    private final EvaluationRepository evaluationRepository;
    private final EvaluationPayload evaluationPayload;

    @Override
    public Mono<Evaluation> create(EvaluationCreatedCommand command) {
        return evaluationPayload.getCode().flatMap(code -> {
            var value = Evaluation.builder()
                    .evaluationId(UUID.randomUUID().toString())
                    .code(code).name(command.name())
                    .profFullname(command.profFullname())
                    .profCode(command.profCode())
                    .noteMax(Double.parseDouble(command.noteMax()))
                    .courseCode(command.courseCode())
                    .courseName(command.courseName())
                    .schoolYear(command.schoolYear())
                    .establishmentCode(command.establishmentCode())
                    .establishmentName(command.establishmentName())
                    .sectionName(command.sectionName())
                    .sectionCode(command.sectionCode())
                    .classroomName(command.classroomName())
                    .classroomCode(command.classroomCode())
                    .trimester(command.semester())
                    .logCreatedAt(LogCreated.At())
                    .logCreatedDate(LogCreated.Date())
                    .logCreatedMonth(LogCreated.Month())
                    .logCreatedYear(LogCreated.Year()).build();
            return evaluationRepository.save(value);
        });
    }

    @Override
    public Mono<Evaluation> update(EvaluationUpdatedCommand command) {
        return evaluationRepository.findEvaluationByCode(command.evaluationCode()).flatMap(evaluation -> {
            evaluation.setNoteMax(Double.parseDouble(command.noteMax()));
            evaluation.setSchoolYear(command.schoolYear());
            evaluation.setTrimester(command.semester());
            return evaluationRepository.save(evaluation);
        });
    }
}
