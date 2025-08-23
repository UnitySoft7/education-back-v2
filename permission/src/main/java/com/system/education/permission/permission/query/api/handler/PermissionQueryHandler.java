package com.system.education.permission.permission.query.api.handler;

import com.system.education.permission.permission.query.api.query.PermissionByCodeAndSemesterQuery;
import com.system.education.permission.permission.query.api.query.PermissionByCodeQuery;
import com.system.education.permission.permission.query.api.query.PermissionByIdQuery;
import com.system.education.permission.permission.query.api.response.NameResponse;
import com.system.education.permission.permission.query.api.response.PermissionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PermissionQueryHandler {
    Flux<PermissionResponse> getPermissions();
    Mono<PermissionResponse> getPermissionById(PermissionByIdQuery query);
    Mono<PermissionResponse> getPermissionByCode(PermissionByCodeQuery query);
    Flux<PermissionResponse> getPermissionByEstablishment(PermissionByCodeQuery query);
    Flux<PermissionResponse> getPermissionByEstablishmentAndSemester(PermissionByCodeAndSemesterQuery query);
    Flux<PermissionResponse> getPermissionByStudentAndSemester(PermissionByCodeAndSemesterQuery query);
    Flux<PermissionResponse> getPermissionByPermissionTypeAndSemester(PermissionByCodeAndSemesterQuery query);
    List<NameResponse> getName();
}
