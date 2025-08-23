package com.system.education.infirmary.diagnosis.query.api.repository;

import com.system.education.infirmary.diagnosis.core.model.Diagnosis;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DiagnosisRepository extends ReactiveMongoRepository<Diagnosis, String> {
    Flux<Diagnosis> findByEstablishmentCodeAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("schoolYear") String schoolYear);
    Flux<Diagnosis> findByStudentCodeAndSchoolYear(@Param("studentCode") String studentCode, @Param("schoolYear") String schoolYear);
    Mono<Boolean> existsByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Mono<Diagnosis> findByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<Diagnosis> findByEstablishmentCodeAndSemesterAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
}
