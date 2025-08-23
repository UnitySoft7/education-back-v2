package com.system.education.infirmary.product.query.api.repository;

import com.system.education.infirmary.product.core.model.InfirmaryProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<InfirmaryProduct, String> {
    Mono<InfirmaryProduct> findByProductCode(@Param("productCode") String productCode);
    Mono<Boolean> existsByProductCode(@Param("productCode") String productCode);
    Flux<InfirmaryProduct> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
}
