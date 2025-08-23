package com.system.education.classes.course.core.payload;

import com.system.education.classes.course.cmd.api.command.ClassCourseCreatedCommand;
import com.system.education.classes.course.cmd.api.command.ClassCourseUpdatedCommand;
import com.system.education.classes.course.query.api.dto.LookupCourseResponse;
import com.system.education.classes.course.query.api.dto.LookupEstablishmentSectionClassResponse;
import reactor.core.publisher.Mono;

public interface ClassCoursePayload {
    Mono<String> getCode();

    Mono<Boolean> isClassCourseExist(String classCourseCode);

    Mono<Boolean> createException(ClassCourseCreatedCommand command);

    Mono<Boolean> updateException(ClassCourseUpdatedCommand command);

    Mono<LookupCourseResponse> verifyCourseCode(String code);

    Mono<LookupEstablishmentSectionClassResponse> verifyEstablishmentSectionClassCode(String code);
}
