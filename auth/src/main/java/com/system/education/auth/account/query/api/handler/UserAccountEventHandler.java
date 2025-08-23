package com.system.education.auth.account.query.api.handler;

import com.system.education.auth.account.cmd.api.command.*;
import com.system.education.auth.account.core.model.UserAccount;
import reactor.core.publisher.Mono;

public interface UserAccountEventHandler {
    Mono<UserAccount> create(UserAccountCreatedRequest event);
    Mono<UserAccount> updateRole(UserAccountUpdateRequest request);
    Mono<UserAccount> updatePassword(PasswordUpdatedRequest event);
    Mono<UserAccount> enable(ChangeUserStatusCommand command);
    Mono<UserAccount> disable(ChangeUserStatusCommand command);
}
