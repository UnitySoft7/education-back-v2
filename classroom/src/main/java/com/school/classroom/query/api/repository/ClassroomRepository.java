package com.school.classroom.query.api.repository;

import com.school.classroom.core.model.Classroom;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClassroomRepository extends ReactiveMongoRepository<Classroom, String> {
    Mono<Boolean> existsClassroomByCode ( @Param("code") String code);
    Mono<Classroom> findClassroomByCode ( @Param("code") String code);
    Mono<Classroom> findClassroomByName( @Param("name") String name);
    Mono<Boolean> existsClassroomByName( @Param("name") String name);
    Flux<Classroom> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Flux<Classroom> findBySectionCode(@Param("sectionCode") String sectionCode);
}
