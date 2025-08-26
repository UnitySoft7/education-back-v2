package com.school.evaluate.query.api.repository;

import com.school.evaluate.core.model.Evaluate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EvaluateRepository extends ReactiveMongoRepository<Evaluate, String> {
    Mono<Boolean> existsEvaluateByCode ( @Param("year") String year);
    Mono<Evaluate> findEvaluateByCode ( @Param("year") String year);
    Flux<Evaluate> findAllEvaluateByStudentCode ( @Param("studentCode") String studentCode);
    Flux<Evaluate> findAllEvaluateByTrimester(@Param("trimestre") String trimestre);
    Flux<Evaluate> findAllEvaluateBySchoolYear(@Param("year") String year);
    @Query("SELECT e.studentCode AS escs, SUM(e.note) AS note FROM evaluate e WHERE e.studentCode LIKE %:studentCode% GROUP BY e.studentCode")
    Flux<Evaluate> sumNotesByEscs(@Param("studentCode") String studentCode);
    Mono<Evaluate> findEvaluateByStudentCodeAndCourseCodeAndTrimesterAndSchoolYear(@Param("student") String student, @Param("course") String course, @Param("trimester") String trimester, @Param("year") String year );
    Flux<Evaluate> findAllEvaluateByEstablishmentCodeAndClassroomCodeAndSectionCodeAndProfCodeAndCourseCodeAndTrimesterAndSchoolYear(@Param("establishment") String establishment, @Param("classroom") String classroom, @Param("section") String section, @Param("prof") String prof, @Param("course") String course, @Param("trimester") String trimester, @Param("year") String year);
    Mono<Boolean> existsByEvaluationCodeAndStudentCode(@Param("evaluationCode") String evaluationCode, @Param("studentCode") String studentCode);
}