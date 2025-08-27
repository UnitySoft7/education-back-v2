package com.school.minos.minos.query.api.repository;

import com.school.minos.minos.core.model.Minos;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MinosRepository extends ReactiveMongoRepository<Minos, String> {
    Mono<Boolean> existsMinosByMinosCode(@Param("code") String code);
    Mono<Minos> findMinosByMinosCode ( @Param("code") String code);
    Mono<Minos> findByStudentCodeAndSemesterAndSchoolYear(
            @Param("studentCode") String studentCode, @Param("semester") String semester,
            @Param("schoolYear") String schoolYear);
    Mono<Boolean> existsByStudentCodeAndSemesterAndSchoolYear(
            @Param("studentCode") String studentCode, @Param("semester") String semester,
            @Param("schoolYear") String schoolYear);
}
