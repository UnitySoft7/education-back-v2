package com.school.bed.query.api.repository;

import com.school.bed.core.model.Bed;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BedRepository extends ReactiveMongoRepository<Bed, String> {
    Mono<Bed> findBedByCode ( @Param("code") String code);
    Mono<Bed> findBedBySigle(@Param("name") String name);
}
