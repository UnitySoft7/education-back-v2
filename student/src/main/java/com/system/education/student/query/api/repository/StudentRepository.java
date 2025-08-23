package com.system.education.student.query.api.repository;

import com.system.education.student.core.model.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
    Mono<Student> findByStudentCode(@Param("studentCode") String studentCode);
    Mono<Boolean> existsByStudentCode(@Param("studentCode") String studentCode);
    Flux<Student> findByParentCode(@Param("parentCode") String parentCode);
    Mono<Boolean> existsByParentCode(@Param("parentCode") String parentCode);
    Flux<Student> findByClassCode(@Param("classCode") String classCode);
    Flux<Student> findBySectionCode(@Param("sectionCode") String sectionCode);
    Flux<Student> findByEstablishmentCode(@Param("establishmentCode") String establishmentCode);
}
