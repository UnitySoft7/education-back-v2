package com.system.education.teacher.query.api.handler;

import com.system.education.teacher.query.api.query.TeacherByCodeQuery;
import com.system.education.teacher.query.api.query.TeacherByIdQuery;
import com.system.education.teacher.query.api.response.NameResponse;
import com.system.education.teacher.query.api.response.TeacherResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TeacherQueryHandler {
    Flux<TeacherResponse> getTeachers();
    Mono<TeacherResponse> getTeacherById(TeacherByIdQuery query);
    Mono<TeacherResponse> getTeacherByCode(TeacherByCodeQuery query);
    Flux<TeacherResponse> getTeacherByEstablishmentCode(TeacherByCodeQuery query);

    List<NameResponse> getFunction();

    Flux<TeacherResponse> getTeacherByTeacher();

    Flux<TeacherResponse> getTeacherByInfirmary();

    Flux<TeacherResponse> getTeacherByCafeteria();

    Flux<TeacherResponse> getTeacherByAdministration();
}
