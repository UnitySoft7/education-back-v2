package com.system.education.auth.account.query.api.handler;

import com.system.education.auth.account.query.api.query.UserByCodeQuery;
import com.system.education.auth.account.query.api.response.AuthRoleResponse;
import com.system.education.auth.account.query.api.response.UserAccountResponse;
import com.system.education.auth.account.query.api.query.UserByIdQuery;
import com.system.education.auth.core.dto.MessageResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserAccountQueryHandler {
    Flux<UserAccountResponse> findAllUser();

    Mono<UserAccountResponse> findUserByID(UserByIdQuery query);

    Flux<UserAccountResponse> findAllUserByEnterprise(UserByCodeQuery query);

    Flux<UserAccountResponse> findAllGlobalUser();

    Flux<UserAccountResponse> findAllNoGlobalUser();

    Mono<AuthRoleResponse> findAuthRole(UserByCodeQuery query);

    Mono<MessageResponse> verifyUsername(String username);
}
