package com.system.education.cafeteria.consumed.query.api.repository;

import com.system.education.cafeteria.consumed.core.model.Consumed;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ConsumedRepository extends ReactiveMongoRepository<Consumed, String> {
    Flux<Consumed> findByStudentCodeAndSchoolYear(@Param("studentCode") String studentCode, @Param("schoolYear") String schoolYear);
    Flux<Consumed> findByProductCode(@Param("productCode") String productCode);
    Flux<Consumed> findByEstablishmentCodeAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("schoolYear") String schoolYear);
    Flux<Consumed> findByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<Consumed> findByEstablishmentCodeAndSemesterAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<Consumed> findByStudentCodeAndSemesterAndStatusAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("status") String status, @Param("schoolYear") String schoolYear);
    Flux<Consumed> findByEstablishmentCodeAndSemesterAndStatusAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("status") String status, @Param("schoolYear") String schoolYear);
}
