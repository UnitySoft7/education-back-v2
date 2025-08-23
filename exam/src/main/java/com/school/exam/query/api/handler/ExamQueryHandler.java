package com.school.exam.query.api.handler;

import com.school.exam.cmd.api.query.FindByCodeAndYearQuery;
import com.school.exam.cmd.api.query.FindByStudentTrimesterYearQuery;
import com.school.exam.cmd.api.query.FindByTrimesterQuery;
import com.school.exam.cmd.api.query.FindByYearQuery;
import com.school.exam.query.api.response.ExamResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface ExamQueryHandler {
    Flux<ExamResponse> findExams ( );

    Mono<ExamResponse> findExamByExamCode (String clothCode );

    Flux<ExamResponse> findAllExamByTrimester(FindByTrimesterQuery query);

    Flux<ExamResponse> findAllExamByYear(FindByYearQuery query);
}