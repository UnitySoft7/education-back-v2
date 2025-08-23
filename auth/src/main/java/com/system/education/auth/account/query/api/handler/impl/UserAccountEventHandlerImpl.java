package com.system.education.auth.account.query.api.handler.impl;

import com.system.education.auth.account.cmd.api.command.*;
import com.system.education.auth.core.common.Status;
import com.system.education.auth.account.core.model.UserAccount;
import com.system.education.auth.account.query.api.handler.UserAccountEventHandler;
import com.system.education.auth.account.query.api.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class UserAccountEventHandlerImpl implements UserAccountEventHandler {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserAccount> create(UserAccountCreatedRequest event) {
        var user = UserAccount.builder()
                .userAccountId(UUID.randomUUID().toString())
                .userCode(event.accountCode())
                .fullName(event.fullName())
                .username(event.username())
                .password(passwordEncoder.encode(event.password()))
                .status(Status.enable())
                .role(event.role())
                .establishmentCode(event.enterpriseCode())
                .establishmentName(event.enterpriseName())
                .build();
        return userAccountRepository.save(user);
    }
    @Override
    public Mono<UserAccount> updateRole(UserAccountUpdateRequest request) {
        return userAccountRepository.findUserAccountByUserCode(request.accountCode())
                .flatMap(userAccount -> {
                    userAccount.setRole(request.role());
                    return userAccountRepository.save(userAccount);
                });
    }
    @Override
    public Mono<UserAccount> updatePassword(PasswordUpdatedRequest event) {
        return userAccountRepository.findUserAccountByUserCode(event.accountCode())
                .flatMap(userAccount -> {
                    userAccount.setPassword(passwordEncoder.encode(event.newPassword()));
                    return userAccountRepository.save(userAccount);
                });
    }
    @Override
    public Mono<UserAccount> enable(ChangeUserStatusCommand command) {
        return userAccountRepository.findUserAccountByUserCode(command.user_code())
                .flatMap(userAccount -> {
                    userAccount.setStatus(Status.enable());
                    return userAccountRepository.save(userAccount);
                });
    }
    @Override
    public Mono<UserAccount> disable(ChangeUserStatusCommand command) {
        return userAccountRepository.findUserAccountByUserCode(command.user_code())
                .flatMap(userAccount -> {
                    userAccount.setStatus(Status.disable());
                    return userAccountRepository.save(userAccount);
                });
    }
}
