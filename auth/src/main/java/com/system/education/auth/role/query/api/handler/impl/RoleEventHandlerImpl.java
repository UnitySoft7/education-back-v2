package com.system.education.auth.role.query.api.handler.impl;

import com.system.education.auth.role.cmd.api.command.RoleCreatedRequest;
import com.system.education.auth.role.cmd.api.command.RoleGlobalCreatedRequest;
import com.system.education.auth.role.cmd.api.command.RoleUpdatedRequest;
import com.system.education.auth.role.core.model.Role;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.query.api.handler.RoleEventHandler;
import com.system.education.auth.role.query.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class RoleEventHandlerImpl implements RoleEventHandler {
    private final RoleRepository roleRepository;
    private final RolePayload rolePayload;

    @Override
    public Mono<Role> create(RoleCreatedRequest request) {
        var role = Role.builder()
                .roleId(UUID.randomUUID().toString())
                .roleName(request.roleName())
                .permissions("")
                .enterpriseCode(request.enterpriseCode())
                .enterpriseName(request.enterpriseName())
                .build();
        return roleRepository.save(role);
    }

    @Override
    public Mono<Role> createGlobal(RoleGlobalCreatedRequest request) {
        var role = Role.builder()
                .roleId(UUID.randomUUID().toString())
                .roleName(request.roleName())
                .permissions("")
                .enterpriseCode("GLOBAL")
                .enterpriseName("GLOBAL")
                .build();
        return roleRepository.save(role);
    }

    @Override
    public Mono<Role> update(RoleUpdatedRequest request) {
        return roleRepository.findById(request.roleId())
                .flatMap(role -> rolePayload.listToString(request.permissions())
                    .flatMap(permissions -> {
                        role.setPermissions(permissions);
                        return roleRepository.save(role);
                    }));
    }
}
