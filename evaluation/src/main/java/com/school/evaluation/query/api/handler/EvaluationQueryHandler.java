package com.school.evaluation.query.api.handler;

import com.school.evaluation.cmd.api.query.FindByCodeQuery;
import com.school.evaluation.cmd.api.query.FindByStudentTrimesterYearQuery;
import com.school.evaluation.cmd.api.query.FindByTrimesterQuery;
import com.school.evaluation.cmd.api.query.FindByYearQuery;
import com.school.evaluation.core.model.Evaluation;
import com.school.evaluation.query.api.response.EvaluationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface EvaluationQueryHandler {
    Flux<EvaluationResponse> findEvaluations();

    Mono<EvaluationResponse> findEvaluationByCode(String code);

//    Flux<EvaluationResponse> findAllEvaluationByECSCS(FindByCodeQuery query);

    Flux<EvaluationResponse> findAllEvaluationByTrimester(FindByTrimesterQuery query);

    Flux<EvaluationResponse> findAllEvaluationByYear(FindByYearQuery query);

    Flux<EvaluationResponse> findAllEvaluationByProfAndSemestreAndYear(FindByStudentTrimesterYearQuery query);
}