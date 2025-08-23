package com.school.examination.core.payload;

import com.school.examination.query.api.dto.LookupCourseTeacherResponse;
import com.school.examination.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.examination.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import reactor.core.publisher.Mono;

public
interface ExaminationPayload {
    Mono<String> getCode ( );

    Mono<Boolean> isExaminationCodeExist ( String ExaminationCode );

    Mono<LookupEstablishmentSectionClassCourseResponse> verifyESCCCode(String code);

    Mono<LookupCourseTeacherResponse> verifyESCCTCode(String code);

    Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code);

    Mono<Boolean> verifyNote(String note, String noteMax);

    Mono<Boolean> verifyExamCodeAndStudent(String examCode, String studentCode);
}