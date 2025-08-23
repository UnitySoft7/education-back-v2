package com.system.education.bulletin.detail.query.api.repository;

import com.system.education.bulletin.detail.core.model.BulletinDetail;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BulletinDetailRepository extends ReactiveMongoRepository<BulletinDetail, String> {
    Flux<BulletinDetail> findByClassCodeAndSemesterAndSchoolYear(
            @Param("classCode") String classCode, @Param("semester") String semester,
            @Param("schoolYear") String schoolYear);
    Flux<BulletinDetail> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Flux<BulletinDetail> findByEstablishmentCodeAndSemesterAndSchoolYear(
            @Param("establishmentCode") String establishmentCode, @Param("semester")
            String semester, @Param("schoolYear") String schoolYear);
    Flux<BulletinDetail> findByStudentCodeAndSemesterAndSchoolYear(
            @Param("studentCode") String studentCode, @Param("semester") String semester,
            @Param("schoolYear") String schoolYear);
    Flux<BulletinDetail> findByCourseCodeAndSemesterAndSchoolYear(
            @Param("courseCode") String courseCode, @Param("semester") String semester,
            @Param("schoolYear") String schoolYear);
   Mono<Boolean> existsByStudentCodeAndCourseCodeAndSemesterAndSchoolYear(
            @Param("studentCode") String studentCode, @Param("courseCode") String courseCode,
            @Param("semester") String semester, @Param("schoolYear") String schoolYear);
}
