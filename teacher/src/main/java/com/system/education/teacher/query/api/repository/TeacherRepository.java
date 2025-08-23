package com.system.education.teacher.query.api.repository;

import com.system.education.teacher.core.model.Teacher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TeacherRepository extends ReactiveMongoRepository<Teacher, String> {
    Mono<Teacher> findByTeacherCode(@Param("teacherCode") String teacherCode);
    Mono<Boolean> existsByTeacherCode(@Param("teacherCode") String teacherCode);
    Flux<Teacher> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
    Flux<Teacher> findByFunction(@Param("function") String function);
}
