package com.system.education.permission.permission.query.api.handler;

import com.system.education.permission.permission.cmd.api.command.PermissionCreatedCommand;
import com.system.education.permission.permission.cmd.api.command.PermissionUpdatedCommand;
import com.system.education.permission.permission.core.model.Permission;
import reactor.core.publisher.Mono;

public interface PermissionEventHandler {
    Mono<Permission> create(PermissionCreatedCommand command);

    Mono<Permission> update(PermissionUpdatedCommand command);
}
