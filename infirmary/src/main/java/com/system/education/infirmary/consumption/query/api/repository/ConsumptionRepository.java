package com.system.education.infirmary.consumption.query.api.repository;

import com.system.education.infirmary.consumption.core.model.InfirmaryConsumption;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ConsumptionRepository extends ReactiveMongoRepository<InfirmaryConsumption, String> {
    Flux<InfirmaryConsumption> findByEstablishmentCodeAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("schoolYear") String schoolYear);
    Flux<InfirmaryConsumption> findByStudentCodeAndSchoolYear(@Param("studentCode") String studentCode, @Param("schoolYear") String schoolYear);
    Mono<Boolean> existsByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Mono<InfirmaryConsumption> findByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<InfirmaryConsumption> findByEstablishmentCodeAndSemesterAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<InfirmaryConsumption> findByEstablishmentCodeAndSemesterAndStatusAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("status") String status, @Param("schoolYear") String schoolYear);
}
