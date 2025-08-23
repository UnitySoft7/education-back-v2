package com.system.education.infirmary.product.core.payload;

import reactor.core.publisher.Mono;

public interface ProductPayload {
    Mono<String> getCode();

    Mono<Boolean> isProductCodeExist(String studentCode);
}
