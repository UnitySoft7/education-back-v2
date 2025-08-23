package com.system.education.course.teacher.query.api.repository;

import com.system.education.course.teacher.core.model.CourseTeacher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CourseTeacherRepository extends ReactiveMongoRepository<CourseTeacher, String> {
    Mono<CourseTeacher> findByCourseTeacherCode(@Param("courseTeacherCode") String courseTeacherCode);
    Mono<Boolean> existsByCourseTeacherCode(@Param("courseTeacherCode") String courseTeacherCode);
    Flux<CourseTeacher> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Flux<CourseTeacher> findByTeacherCode(@Param("teacherCode") String teacherCode);
    Flux<CourseTeacher> findByClassCode(@Param("classCode") String classCode);
}
