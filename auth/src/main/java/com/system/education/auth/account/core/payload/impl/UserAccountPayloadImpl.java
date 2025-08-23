package com.system.education.auth.account.core.payload.impl;

import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.account.core.payload.UserAccountPayload;
import com.system.education.auth.account.query.api.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserAccountPayloadImpl implements UserAccountPayload {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<MessageResponse> regexpUserAccountId(String roleId) {
        String regexpUserId = "^[a-z0-9-]{36}$";
        Pattern patternUserId = Pattern.compile(regexpUserId);
        Matcher matcherUserId = patternUserId.matcher(roleId);
        if (!matcherUserId.matches())
            return Mono.just(new MessageResponse(false, "Ne doit contenir que trente-six caractères"));
        return Mono.just(new MessageResponse(true, "OK"));
    }
    @Override
    public Mono<MessageResponse> verifyingUserAccountId(String userId) {
        return userAccountRepository.existsById(userId)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just(new MessageResponse(true, "OK"));
                    } else {
                        return Mono.just(new MessageResponse( false, "L'utilisateur n'existe pas"));
                    }
                });
    }
    @Override
    public Mono<MessageResponse> verifyAccountCode(String accountCode) {
        return userAccountRepository.existsUserAccountByUserCode(accountCode)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just(new MessageResponse( false, "Le code de compte existe déjà"));
                    } else {
                        return Mono.just(new MessageResponse(true, "OK"));
                    }
                });
    }
    @Override
    public Mono<MessageResponse> verifyPassword(String newPassword, String verifyPassword) {
        if (!Objects.equals(newPassword, verifyPassword)){
            return Mono.just(new MessageResponse( false, "Les mots de passe ne correspondent pas"));
        } else {
            return Mono.just(new MessageResponse(true, "OK"));
        }
    }
    @Override
    public Mono<MessageResponse> verifyOldPassword(String accountCode, String oldPassword) {
        return userAccountRepository.findUserAccountByUserCode(accountCode)
                .flatMap(user -> {
                    if (user == null) {
                        return Mono.just(new MessageResponse( false, "Utilisateur non trouvé"));
                    } else if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                        return Mono.just(new MessageResponse( false, "Mot de passe incorrect"));
                    } else {
                        return Mono.just(new MessageResponse(true, "OK"));
                    }
                });
    }
    @Override
    public Mono<Boolean> existsUserAccountByUsername(String username) {
        return userAccountRepository.existsUserAccountByUsername(username);
    }
}
