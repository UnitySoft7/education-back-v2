package com.system.education.auth.account.core.payload;

import com.system.education.auth.core.dto.MessageResponse;
import reactor.core.publisher.Mono;

public interface UserAccountPayload {
    Mono<MessageResponse> regexpUserAccountId(String roleId);
    Mono<MessageResponse> verifyingUserAccountId(String userId);
    Mono<MessageResponse> verifyAccountCode(String accountCode);
    Mono<MessageResponse> verifyPassword(String newPassword, String verifyPassword);
    Mono<MessageResponse> verifyOldPassword(String username, String oldPassword);
    Mono<Boolean> existsUserAccountByUsername(String username);
}
