package com.system.education.bulletin.bulletin.core.payload;

import reactor.core.publisher.Mono;

public interface BulletinPayload {
    Mono<String> getCode();

    Mono<Boolean> isBulletinCodeExist(String bulletinCode);
}
