package com.system.education.auth.role.query.api.handler;

import com.system.education.auth.role.query.api.query.RoleByCodeQuery;
import com.system.education.auth.role.query.api.query.RoleByIdQuery;
import com.system.education.auth.role.query.api.response.GetRoleNameResponse;
import com.system.education.auth.role.query.api.response.NameResponse;
import com.system.education.auth.role.query.api.response.RoleResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleQueryHandler {
    Flux<RoleResponse> findAllRole();

    Flux<RoleResponse> findAllRolesNotGlobal();

    Flux<RoleResponse> findAllRolesGlobal();

    Mono<RoleResponse> findRoleByID(RoleByIdQuery query);

    Flux<RoleResponse> findRolesByEnterprise(RoleByCodeQuery query);

    Mono<List<NameResponse>> getAllPermissionNames();

    Mono<List<NameResponse>> getAllGlobalPermissionNames();
}
