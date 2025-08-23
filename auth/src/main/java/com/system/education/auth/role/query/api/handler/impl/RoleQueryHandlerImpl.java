package com.system.education.auth.role.query.api.handler.impl;

import com.system.education.auth.role.core.model.Role;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.core.util.Permissions;
import com.system.education.auth.role.query.api.handler.RoleQueryHandler;
import com.system.education.auth.role.query.api.query.RoleByCodeQuery;
import com.system.education.auth.role.query.api.query.RoleByIdQuery;
import com.system.education.auth.role.query.api.repository.RoleRepository;
import com.system.education.auth.role.query.api.response.GetRoleNameResponse;
import com.system.education.auth.role.query.api.response.NameResponse;
import com.system.education.auth.role.query.api.response.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class RoleQueryHandlerImpl implements RoleQueryHandler {
    private final RoleRepository roleRepository;
    private final RolePayload rolePayload;

    @Override
    public Flux<RoleResponse> findAllRole() {
        return roleRepository.findAll().flatMap(this::getRole);
    }

    @Override
    public Flux<RoleResponse> findAllRolesNotGlobal() {
        return roleRepository.findRoleByEnterpriseCodeNot("GLOBAL").flatMap(this::getRole);
    }

    @Override
    public Flux<RoleResponse> findAllRolesGlobal() {
        return roleRepository.findRoleByEnterpriseCode("GLOBAL").flatMap(this::getRole);
    }

    @Override
    public Mono<RoleResponse> findRoleByID(RoleByIdQuery query) {
        return roleRepository.findById(query.roleId())
                .flatMap(this::getRole);
    }

    @Override
    public Flux<RoleResponse> findRolesByEnterprise(RoleByCodeQuery query) {
        return roleRepository.findRoleByEnterpriseCode(query.code())
                .flatMap(this::getRole);
    }

    private Mono<RoleResponse> getRole(Role role) {
        return Mono.just(new RoleResponse(role.getRoleId(), role.getRoleName(),
                rolePayload.stringToList(role.getPermissions()),
                role.getEnterpriseName(), role.getEnterpriseCode()));
    }

    @Override
    public Mono<List<NameResponse>> getAllPermissionNames() {
        List<NameResponse> permissions = new ArrayList<>();
        permissions.add(new NameResponse(Permissions.user_view));
        permissions.add(new NameResponse(Permissions.user_create));
        permissions.add(new NameResponse(Permissions.user_update));
        permissions.add(new NameResponse(Permissions.user_enable));
        permissions.add(new NameResponse(Permissions.user_disable));
        permissions.add(new NameResponse(Permissions.role_view));
        permissions.add(new NameResponse(Permissions.role_create));
        permissions.add(new NameResponse(Permissions.role_update));
        permissions.add(new NameResponse(Permissions.all));
        return Mono.just(permissions);
    }

    @Override
    public Mono<List<NameResponse>> getAllGlobalPermissionNames() {
        List<NameResponse> permissions = new ArrayList<>();
        permissions.add(new NameResponse(Permissions.user_global));
        permissions.add(new NameResponse(Permissions.role_global));
        permissions.add(new NameResponse(Permissions.all_global));
        return Mono.just(permissions);
    }
}
