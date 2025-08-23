package com.school.exam.query.api.repository;

import com.school.exam.core.model.Exam;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ExamRepository extends ReactiveMongoRepository<Exam, String> {
    Mono<Boolean> existsExamByCode ( @Param("examCode") String code);
    Mono<Exam> findExamByCode ( @Param("examCode") String code);
    Flux<Exam> findExamByTrimester(@Param("trimester") String trimester);
    Flux<Exam> findExamBySchoolYear(@Param("year")String year);
    Flux<Exam> findAllExamByProfCodeAndTrimesterAndSchoolYear(@Param("code") String code, @Param("semestre") String semestre, @Param("year") String year);


}
