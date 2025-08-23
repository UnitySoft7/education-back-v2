package com.system.education.parent.core.payload;

import com.system.education.parent.core.dto.MessageResponse;
import reactor.core.publisher.Mono;

public interface ParentPayload {
    Mono<String> getCode();

    Mono<Boolean> isParentCodeExist(String parentCode);

    Mono<MessageResponse> verifyPassword(String newPassword, String verifyPassword);
}
