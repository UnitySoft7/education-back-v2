package com.system.education.cafeteria.product.query.api.repository;

import com.system.education.cafeteria.product.core.model.Product;
import reactor.core.publisher.Mono;

public interface ProductRepositories {
    Mono<Product> getLastProduct();
}
