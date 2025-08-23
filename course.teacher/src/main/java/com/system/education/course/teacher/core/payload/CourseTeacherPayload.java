package com.system.education.course.teacher.core.payload;

import com.system.education.course.teacher.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.system.education.course.teacher.query.api.dto.LookupTeacherResponse;
import reactor.core.publisher.Mono;

public interface CourseTeacherPayload {
    Mono<String> getCode();

    Mono<Boolean> isEstablishmentSectionCodeClassCourseExist(String establishmentSectionClassCourseCode);

    Mono<LookupTeacherResponse> verifyTeacherCode(String code);

    Mono<LookupEstablishmentSectionClassCourseResponse> verifyEstablishmentSectionClassCourseCode(String code);
}
