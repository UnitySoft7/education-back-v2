package com.school.examination.query.api.handler;

import com.school.examination.cmd.api.query.*;
import com.school.examination.core.model.Examination;
import com.school.examination.query.api.response.ExaminationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface ExaminationQueryHandler {
    Flux<ExaminationResponse> findExaminations();
//    Flux<ExaminationResponse> findAllExaminationsByESCC(FindByCodeQuery query);
//    Flux<ExaminationResponse> findAllExaminationsByESCS(FindByCodeQuery query);
//    Flux<ExaminationResponse> findAllExaminationsByESCCT(FindByCodeQuery query);
    Flux<ExaminationResponse> findAllExaminationsByTrimester(FindByTrimesterQuery query);

    Flux<ExaminationResponse> findAllExaminationsByYear(FindByYearQuery query);

    Flux<ExaminationResponse> findAllExaminationsByECSP(FindByEstablishClassSectionProfCourseTrimesterYearQuery query);

    Mono<ExaminationResponse> findAllExaminationsBySCSY(FindByStudentCourseTrimesterYearQuery query);

    Mono<ExaminationResponse> findExaminationByExaminationCode(String code);

    ExaminationResponse getExamination(Examination examination);
}