package com.system.education.cafeteria.product.query.api.handler;

import com.system.education.cafeteria.product.query.api.query.ProductByCodeQuery;
import com.system.education.cafeteria.product.query.api.query.ProductByIdQuery;
import com.system.education.cafeteria.product.query.api.response.ProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductQueryHandler {
    Flux<ProductResponse> getProducts();
    Mono<ProductResponse> getProductById(ProductByIdQuery query);
    Mono<ProductResponse> getProductByCode(ProductByCodeQuery query);
    Flux<ProductResponse> getProductByEstablishment(ProductByCodeQuery query);
}
