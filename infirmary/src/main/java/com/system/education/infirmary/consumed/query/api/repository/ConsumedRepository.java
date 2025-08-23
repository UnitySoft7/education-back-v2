package com.system.education.infirmary.consumed.query.api.repository;

import com.system.education.infirmary.consumed.core.model.InfirmaryConsumed;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ConsumedRepository extends ReactiveMongoRepository<InfirmaryConsumed, String> {
    Flux<InfirmaryConsumed> findByStudentCodeAndSchoolYear(@Param("studentCode") String studentCode, @Param("schoolYear") String schoolYear);
    Flux<InfirmaryConsumed> findByDiagnosisCode(@Param("diagnosisCode") String diagnosisCode);
    Flux<InfirmaryConsumed> findByEstablishmentCodeAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("schoolYear") String schoolYear);
    Flux<InfirmaryConsumed> findByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<InfirmaryConsumed> findByEstablishmentCodeAndSemesterAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<InfirmaryConsumed> findByStudentCodeAndSemesterAndStatusAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("status") String status, @Param("schoolYear") String schoolYear);
    Flux<InfirmaryConsumed> findByEstablishmentCodeAndSemesterAndStatusAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("status") String status, @Param("schoolYear") String schoolYear);
}
