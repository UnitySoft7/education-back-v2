package com.system.education.permission.permission.core.payload;

import reactor.core.publisher.Mono;

public interface PermissionPayload {
    Mono<String> getCode();

    Mono<Boolean> isPermissionCodeExist(String productCode);
}
