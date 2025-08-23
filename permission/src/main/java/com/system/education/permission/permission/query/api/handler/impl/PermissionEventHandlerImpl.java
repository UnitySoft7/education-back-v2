package com.system.education.permission.permission.query.api.handler.impl;

import com.system.education.permission.core.common.LogCreated;
import com.system.education.permission.permission.query.api.handler.PermissionEventHandler;
import com.system.education.permission.permission.cmd.api.command.PermissionCreatedCommand;
import com.system.education.permission.permission.cmd.api.command.PermissionUpdatedCommand;
import com.system.education.permission.permission.core.model.Permission;
import com.system.education.permission.permission.core.payload.PermissionPayload;
import com.system.education.permission.permission.query.api.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PermissionEventHandlerImpl implements PermissionEventHandler {
    private final PermissionRepository permissionRepository;
    private final PermissionPayload permissionPayload;

    @Override
    public Mono<Permission> create(PermissionCreatedCommand command) {
        return  permissionPayload.getCode()
                .flatMap(code -> {
                    Permission permission = Permission.builder()
                            .permissionId(UUID.randomUUID().toString())
                            .permissionCode(code)
                            .permissionType(command.permissionType())
                            .description(command.description())
                            .startOn(command.startOn())
                            .endOn(command.endOn())
                            .studentCode(command.studentCode())
                            .studentName(command.studentName())
                            .semester(command.semester())
                            .schoolYear(command.schoolYear())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();
                    return permissionRepository.save(permission);
                });
    }

    @Override
    public Mono<Permission> update(PermissionUpdatedCommand command) {
         return permissionRepository.findByPermissionCode(command.permissionCode())
                .flatMap(permission -> {
                    permission.setPermissionType(command.permissionTypeCode());
                    permission.setDescription(command.description());
                    permission.setStudentCode(command.studentCode());
                    permission.setStudentName(command.studentName());
                    permission.setStartOn(command.startOn());
                    permission.setEndOn(command.endOn());
                    return permissionRepository.save(permission);
                });
    }
}
