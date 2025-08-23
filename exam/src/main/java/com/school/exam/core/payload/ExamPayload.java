package com.school.exam.core.payload;

import com.school.exam.query.api.dto.LookupCourseTeacherResponse;
import com.school.exam.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.exam.query.api.dto.LookupEstablishmentSectionClassResponse;
import reactor.core.publisher.Mono;

public
interface ExamPayload {
    Mono<String> getCode ( );

    Mono<Boolean> isExamCodeExist ( String ExamCode );

    Mono<LookupEstablishmentSectionClassCourseResponse> verifyESCCCode(String code);

    Mono<LookupCourseTeacherResponse> verifyESCCTCode(String code);

    Mono<LookupEstablishmentSectionClassResponse> verifyESCCode(String code);

    Mono<Boolean> varifyNote(String note);
}