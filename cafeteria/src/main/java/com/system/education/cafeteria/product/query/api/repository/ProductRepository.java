package com.system.education.cafeteria.product.query.api.repository;

import com.system.education.cafeteria.product.core.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Mono<Product> findByProductCode(@Param("productCode") String productCode);
    Mono<Boolean> existsByProductCode(@Param("productCode") String productCode);
    Flux<Product> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
}
