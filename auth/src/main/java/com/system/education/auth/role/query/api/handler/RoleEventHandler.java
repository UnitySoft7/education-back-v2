package com.system.education.auth.role.query.api.handler;

import com.system.education.auth.role.cmd.api.command.RoleCreatedRequest;
import com.system.education.auth.role.cmd.api.command.RoleGlobalCreatedRequest;
import com.system.education.auth.role.cmd.api.command.RoleUpdatedRequest;
import com.system.education.auth.role.core.model.Role;
import reactor.core.publisher.Mono;

public interface RoleEventHandler {
    Mono<Role> create(RoleCreatedRequest request);

    Mono<Role> createGlobal(RoleGlobalCreatedRequest request);

    Mono<Role> update(RoleUpdatedRequest event);
}
