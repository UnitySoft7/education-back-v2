package com.system.education.cafeteria.consumption.query.api.repository;

import com.system.education.cafeteria.consumption.core.model.Consumption;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ConsumptionRepository extends ReactiveMongoRepository<Consumption, String> {
    Flux<Consumption> findByEstablishmentCodeAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("schoolYear") String schoolYear);
    Flux<Consumption> findByStudentCodeAndSchoolYear(@Param("studentCode") String studentCode, @Param("schoolYear") String schoolYear);
    Mono<Boolean> existsByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Mono<Consumption> findByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<Consumption> findByEstablishmentCodeAndSemesterAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<Consumption> findByEstablishmentCodeAndSemesterAndStatusAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("status") String status, @Param("schoolYear") String schoolYear);
}
