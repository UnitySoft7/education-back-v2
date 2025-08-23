package com.system.education.auth.role.core.payload.impl;

import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.role.cmd.api.command.RoleCreatedRequest;
import com.system.education.auth.role.cmd.api.command.RoleGlobalCreatedRequest;
import com.system.education.auth.role.cmd.api.command.RoleUpdatedRequest;
import com.system.education.auth.role.core.exception.RoleNameExceptionResponse;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.query.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RolePayloadImpl implements RolePayload {
    private final RoleRepository roleRepository;

    @Override
    public Mono<MessageResponse> verifyingRoleId(String roleId) {
        return roleRepository.existsById(roleId)
                .flatMap(aBoolean -> {
                    if (aBoolean) {
                        return Mono.just(new MessageResponse(true, "OK"));
                    } else {
                        return Mono.just(new MessageResponse(false, "Le rôle n'existe pas"));
                    }
                });
    }

    @Override
    public Mono<MessageResponse> regexpRoleId(String roleId) {
        String regexpRoleId = "^[a-z0-9-]{36}$";
        Pattern patternRoleId = Pattern.compile(regexpRoleId);
        Matcher matcherRoleId = patternRoleId.matcher(roleId);
        if (!matcherRoleId.matches())
            return Mono.just(new MessageResponse(false, "Ne doit contenir que trente-six caractères"));
        return Mono.just(new MessageResponse(true, "OK"));
    }

    @Override
    public Mono<MessageResponse> exception(RoleCreatedRequest request) {
        if (request.roleName() == null)
            return Mono.just(new MessageResponse(false, "Vous devez ramplir tous les champs"));
        return roleRepository.existsRoleByRoleNameAndEnterpriseCode(request.roleName(), request.enterpriseCode())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RoleNameExceptionResponse("Le nom du rôle existe déjà"));
                    }
                    return Mono.just(new MessageResponse(true, "OK"));
                });

    }

    @Override
    public Mono<MessageResponse> exception(RoleGlobalCreatedRequest request) {
        if (request.roleName() == null)
            return Mono.just(new MessageResponse(false, "Vous devez ramplir tous les champs"));
        return roleRepository.existsRoleByRoleNameAndEnterpriseCode(request.roleName(), "GLOBAL")
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RoleNameExceptionResponse("Le nom du rôle existe déjà"));
                    }
                    return Mono.just(new MessageResponse(true, "OK"));
                });

    }

    @Override
    public Mono<MessageResponse> exception(RoleUpdatedRequest request) {
        return roleRepository.findById(request.roleId())
                .flatMap(role -> {
                    if (role == null) {
                        return Mono.just(new MessageResponse(true, "La role n'existe pas"));
                    } else {
                        if (request.permissions() == null)
                            return Mono.just(new MessageResponse(true, "Vous devez ramplir tous les champs"));

                        return roleRepository.findRoleByRoleNameAndEnterpriseCode(request.roleName(), role.getEnterpriseCode())
                                .flatMap(existingRoleDescription ->
                                        roleRepository.existsRoleByRoleNameAndEnterpriseCode(request.roleName(), role.getEnterpriseCode())
                                                .flatMap(exists -> {
                                                    if (exists && !role.getRoleId().equals(existingRoleDescription.getRoleId()))
                                                        return Mono.error(new RoleNameExceptionResponse("La role existe déjà"));
                                                    return Mono.just(new MessageResponse(true, "OK"));
                                                }));
                    }
                });
    }

    @Override
    public Mono<String> listToString(List<String> permissions) {
        return Mono.just(String.join("__", permissions));
    }

    @Override
    public List<String> stringToList(String permissions) {
        return List.of(permissions.split("__"));
    }

}
