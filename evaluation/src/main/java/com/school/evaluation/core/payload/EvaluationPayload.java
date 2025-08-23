package com.school.evaluation.core.payload;

import com.school.evaluation.query.api.dto.LookupCourseTeacherResponse;
import com.school.evaluation.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.evaluation.query.api.dto.LookupEstablishmentSectionClassResponse;
import com.school.evaluation.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import reactor.core.publisher.Mono;

public
interface EvaluationPayload {
    Mono<String> getCode ( );

    Mono<Boolean> isEvaluationCodeExist ( String EvaluationCode );

    Mono<LookupEstablishmentSectionClassCourseResponse> verifyESCCCode(String code);

    Mono<LookupCourseTeacherResponse> verifyESCCTCode(String code);

    Mono<LookupEstablishmentSectionClassResponse> verifyESCCode(String code);

    Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code);
}