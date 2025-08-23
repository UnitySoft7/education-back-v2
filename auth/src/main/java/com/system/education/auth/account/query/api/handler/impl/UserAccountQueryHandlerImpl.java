package com.system.education.auth.account.query.api.handler.impl;

import com.system.education.auth.account.core.model.UserAccount;
import com.system.education.auth.account.query.api.handler.UserAccountQueryHandler;
import com.system.education.auth.account.query.api.query.UserByCodeQuery;
import com.system.education.auth.account.query.api.query.UserByIdQuery;
import com.system.education.auth.account.query.api.repository.UserAccountRepository;
import com.system.education.auth.account.query.api.response.AuthRoleResponse;
import com.system.education.auth.account.query.api.response.UserAccountResponse;
import com.system.education.auth.core.common.Status;
import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.query.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Component
@RequiredArgsConstructor
public class UserAccountQueryHandlerImpl implements UserAccountQueryHandler {
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final RolePayload rolePayload;

    @Override
    public Flux<UserAccountResponse> findAllUser() {
        return userAccountRepository.findAll()
                .flatMap(this::getUserAccount);
    }
    @Override
    public Mono<UserAccountResponse> findUserByID(UserByIdQuery query) {
        return userAccountRepository.findById(query.userId())
                .flatMap(this::getUserAccount);
    }
    private Mono<UserAccountResponse> getUserAccount(UserAccount userAccount) {
        return roleRepository.findById(userAccount.getRole())
                .flatMap(role -> {
                    String roleName = role.getRoleName();
                    return Mono.just(new UserAccountResponse(userAccount.getUserAccountId(),
                            userAccount.getUserCode(), userAccount.getFullName(),
                            userAccount.getUsername(), userAccount.getRole(),
                            roleName, getStatus(userAccount.getStatus())));
                });
    }

    @Override
    public Flux<UserAccountResponse> findAllUserByEnterprise(UserByCodeQuery query) {
        return userAccountRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getUserAccount);
    }

    @Override
    public Flux<UserAccountResponse> findAllGlobalUser() {
        return userAccountRepository.findByEstablishmentCode("GLOBAL")
                .flatMap(this::getUserAccount);
    }

    @Override
    public Flux<UserAccountResponse> findAllNoGlobalUser() {
        return userAccountRepository.findByEstablishmentCodeNot("GLOBAL")
                .flatMap(this::getUserAccount);
    }

    private String getStatus(String status) {
        if (status.equals(Status.enable()))
            return "Active";
        else return "Desactive";
    }

    @Override
    public Mono<AuthRoleResponse> findAuthRole(UserByCodeQuery query) {
        return userAccountRepository.findUserAccountByUserCode(query.code())
                .flatMap(userAccount -> roleRepository.findById(userAccount.getRole())
                        .flatMap(role -> Mono.just(new AuthRoleResponse(
                                userAccount.getUserAccountId(),
                                userAccount.getUserCode(),
                                userAccount.getUsername(),
                                userAccount.getFullName(),
                                role.getRoleName(),
                                rolePayload.stringToList(role.getPermissions()),
                                userAccount.getEstablishmentCode(),
                                userAccount.getEstablishmentName()
                        ))));
    }

    @Override
    public Mono<MessageResponse> verifyUsername(String username) {
        return userAccountRepository.existsUserAccountByUsername(username)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just(new MessageResponse(false, "Username already exists"));
                    } else {
                        return Mono.just(new MessageResponse(true, "Username is available"));
                    }
                });
    }
}
