package com.system.education.classes.course.query.api.repository;

import com.system.education.classes.course.core.model.ClassCourse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClassCourseRepository extends ReactiveMongoRepository<ClassCourse, String> {
    Mono<ClassCourse> findByClassCourseCode(@Param("classCourseCode") String classCourseCode);
    Mono<Boolean> existsByClassCourseCode(@Param("classCourseCode") String classCourseCode);
    Flux<ClassCourse> findByClassCode(@Param("classCode") String classCode);
    Flux<ClassCourse> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Mono<Boolean> existsByClassCodeAndCourseCode(@Param("classCode") String classCode, @Param("courseCode") String courseCode);
    Mono<ClassCourse> findByClassCodeAndCourseCode(@Param("classCode") String classCode, @Param("courseCode") String courseCode);
}
