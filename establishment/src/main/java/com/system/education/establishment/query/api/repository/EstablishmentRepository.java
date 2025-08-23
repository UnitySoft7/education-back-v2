package com.system.education.establishment.query.api.repository;

import com.system.education.establishment.core.model.Establishment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EstablishmentRepository extends ReactiveMongoRepository<Establishment, String> {
    Mono<Establishment> findByEstablishmentName(@Param("establishmentName") String establishmentName);
    Mono<Boolean> existsByEstablishmentName(@Param("establishmentName") String establishmentName);
    Mono<Establishment> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Mono<Boolean> existsByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Mono<Boolean> existsByEstablishmentCodeAndState(@Param("establishmentCode") String establishmentCode, @Param("state") String state);
}
