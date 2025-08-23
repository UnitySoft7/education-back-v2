package com.system.education.bulletin.bulletin.query.api.repository;

import com.system.education.bulletin.bulletin.core.model.Bulletin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BulletinRepository extends ReactiveMongoRepository<Bulletin, String> {
    Mono<Bulletin> findByBulletinCode(@Param("bulletinCode") String bulletinCode);
    Mono<Boolean> existsByBulletinCode(@Param("bulletinCode") String bulletinCode);
    Mono<Bulletin> findByStudentCodeAndSemesterAndSchoolYear(
            @Param("studentCode") String studentCode, @Param("semester") String semester,
            @Param("schoolYear") String schoolYear);
    Mono<Boolean> existsByStudentCodeAndSemesterAndSchoolYear(
            @Param("studentCode") String studentCode, @Param("semester") String semester,
            @Param("schoolYear") String schoolYear);
    Flux<Bulletin> findByClassCodeAndSemesterAndSchoolYear(
            @Param("classCode") String studentCode, @Param("semester") String semester,
            @Param("schoolYear") String schoolYear);
    Flux<Bulletin> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
}
