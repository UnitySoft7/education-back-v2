package com.school.minos.tariff.query.api.repository;

import com.school.minos.tariff.core.model.Tariff;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TariffRepository extends ReactiveMongoRepository<Tariff, String> {
    Mono<Boolean> existsTariffByTariffCode(@Param("code") String code);
    Mono<Tariff> findTariffByTariffCode (@Param("code") String code);
    Mono<Tariff> findTariffByClassCode (@Param("classCode") String classCode);
    Flux<Tariff> findTariffByEstablishmentCode (@Param("establishmentCode") String establishmentCode);
}
