package com.school.evaluation.query.api.handler.impl;

import com.school.evaluation.cmd.api.query.FindByCodeQuery;
import com.school.evaluation.cmd.api.query.FindByStudentTrimesterYearQuery;
import com.school.evaluation.cmd.api.query.FindByTrimesterQuery;
import com.school.evaluation.cmd.api.query.FindByYearQuery;
import com.school.evaluation.core.model.Evaluation;
import com.school.evaluation.query.api.handler.EvaluationQueryHandler;
import com.school.evaluation.query.api.repository.EvaluationRepository;
import com.school.evaluation.query.api.response.EvaluationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EvaluationQueryHandlerImpl implements EvaluationQueryHandler {
    private final EvaluationRepository evaluationRepository;

    @Override
    public Flux<EvaluationResponse> findEvaluations() {
        return evaluationRepository.findAll().map(this::getEvaluation);
    }

    @Override
    public Mono<EvaluationResponse> findEvaluationByCode(String code) {
        return evaluationRepository.findEvaluationByCode(code).map(this::getEvaluation);
    }
//    @Override
//    public Flux<EvaluationResponse> findAllEvaluationByECSCS(FindByCodeQuery query) {
//            return evaluationRepository.findAllEvaluationByEStudent(query.code()).map(this::getEvaluation);
//    }

    @Override
    public Flux<EvaluationResponse> findAllEvaluationByTrimester(FindByTrimesterQuery query) {
        return evaluationRepository.findAllEvaluationByTrimester(query.trimester()).map(this::getEvaluation);
    }


    @Override
    public Flux<EvaluationResponse> findAllEvaluationByYear(FindByYearQuery query) {
        return evaluationRepository.findAllEvaluationBySchoolYear(query.year()).map(this::getEvaluation);
    }


    @Override
    public Flux<EvaluationResponse> findAllEvaluationByProfAndSemestreAndYear(FindByStudentTrimesterYearQuery query) {
        return evaluationRepository.findAllEvaluationByProfCodeAndTrimesterAndSchoolYear(query.code(), query.semestre(), query.year()).map(this::getEvaluation);
    }

    private EvaluationResponse getEvaluation(Evaluation evaluation) {
        return new EvaluationResponse(evaluation.getEvaluationId(), evaluation.getName(), evaluation.getCode(), evaluation.getProfFullname(), evaluation.getProfCode(), evaluation.getNoteMax(),
//                evaluation.getESCC(), evaluation.getESC(), evaluation.getESCCT(), evaluation.getESCS(),
                evaluation.getCourseName(), evaluation.getCourseCode(), evaluation.getEstablishmentName(), evaluation.getEstablishmentCode(), evaluation.getSectionName(), evaluation.getSectionCode(), evaluation.getClassroomName(), evaluation.getClassroomCode(), evaluation.getTrimester(), evaluation.getSchoolYear(), evaluation.getLogCreatedAt(), evaluation.getLogCreatedDate(), evaluation.getLogCreatedMonth(), evaluation.getLogCreatedYear());
    }
}
