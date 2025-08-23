package com.school.evaluate.query.api.handler;

import com.school.evaluate.cmd.api.command.FindByStudentTrimesterYearQuery;
import com.school.evaluate.cmd.api.query.FindByEstablishClassSectionProfCourseTrimesterYearQuery;
import com.school.evaluate.cmd.api.query.FindByStudentCourseTrimesterYearQuery;
import com.school.evaluate.cmd.api.query.FindByTrimesterQuery;
import com.school.evaluate.cmd.api.query.FindByYearQuery;
import com.school.evaluate.core.model.Evaluate;
import com.school.evaluate.query.api.response.EvaluateMaxResponse;
import com.school.evaluate.query.api.response.EvaluateResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface EvaluateQueryHandler {
    Flux<EvaluateResponse> findAllEvaluates();

    Flux<EvaluateResponse> findAllEvaluateByStudent(String query);

    Flux<EvaluateResponse> findAllEvaluateByTrimester(FindByTrimesterQuery query);

    Flux<EvaluateResponse> findAllEvaluateByEstaClassSectionProfSemeYear(FindByEstablishClassSectionProfCourseTrimesterYearQuery query);

    Flux<EvaluateResponse> findAllEvaluateByYear(FindByYearQuery query);

    Flux<EvaluateMaxResponse> findMaxEvaluateByCode(String studentCode);

    Mono<Double> findSumNotesByCode(String studentCode);

    Mono<EvaluateResponse> findEvaluateByCode(String code);

    Mono<EvaluateResponse> findAllExaminationsBySCSY(FindByStudentCourseTrimesterYearQuery query);

    EvaluateMaxResponse getMaxEvaluate(Evaluate evaluate);

    EvaluateResponse getEvaluate(Evaluate evaluate);
}