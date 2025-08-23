package com.school.evaluate.core.payload;

import com.school.evaluate.query.api.dto.LookupCourseTeacherResponse;
import com.school.evaluate.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.evaluate.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import reactor.core.publisher.Mono;

public
interface EvaluatePayload {
    Mono<String> getCode ( );

    Mono<Boolean> isEvaluateCodeExist ( String EvaluateCode );

    Mono<LookupEstablishmentSectionClassCourseResponse> verifyESCCCode(String code);

    Mono<LookupCourseTeacherResponse> verifyESCCTCode(String code);

    Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code);

    Mono<Boolean> verifyNote(String note, String noteMax);

    Mono<Boolean> verifyEvaluate(String evaluationCode, String studentCode);
}