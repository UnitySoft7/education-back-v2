package com.system.education.infirmary.product.query.api.repository;

import com.system.education.infirmary.product.core.model.InfirmaryProduct;
import reactor.core.publisher.Mono;

public interface ProductRepositories {
    Mono<InfirmaryProduct> getLastProduct();
}
