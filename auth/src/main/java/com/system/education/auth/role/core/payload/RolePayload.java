package com.system.education.auth.role.core.payload;

import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.role.cmd.api.command.RoleCreatedRequest;
import com.system.education.auth.role.cmd.api.command.RoleGlobalCreatedRequest;
import com.system.education.auth.role.cmd.api.command.RoleUpdatedRequest;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RolePayload {
    Mono<MessageResponse> verifyingRoleId(String roleId);
    Mono<MessageResponse> regexpRoleId(String roleId);
    Mono<MessageResponse> exception(RoleCreatedRequest request);

    Mono<MessageResponse> exception(RoleGlobalCreatedRequest request);

    Mono<MessageResponse> exception(RoleUpdatedRequest request);

    Mono<String> listToString(List<String> permissions);

    List<String> stringToList(String permissions);
}
