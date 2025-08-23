package com.system.education.skill.detail.query.api.repository;

import com.system.education.skill.detail.core.model.SkillDetail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SkillDetailRepository extends ReactiveMongoRepository<SkillDetail, String> {
    Flux<SkillDetail> findBySkillCodeAndSemesterAndSchoolYear(@Param("skillCode") String skillCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<SkillDetail> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Flux<SkillDetail> findByEstablishmentCodeAndSemesterAndSchoolYear(@Param("establishmentCode") String establishmentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
    Flux<SkillDetail> findByStudentCodeAndSemesterAndSchoolYear(@Param("studentCode") String studentCode, @Param("semester") String semester, @Param("schoolYear") String schoolYear);
}
