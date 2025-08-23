package com.school.section.query.api.repository;

import com.school.section.core.model.Section;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SectionRepository extends ReactiveMongoRepository<Section, String> {
    Mono<Boolean> existsSectionBySectionCode(@Param("code") String code);
    Mono<Section> findSectionBySectionCode(@Param("code") String code);
    Mono<Boolean> existsSectionByName(@Param("name") String name);
    Mono<Section> findSectionByName(@Param("name") String name);
    Flux<Section> findSectionByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
}
