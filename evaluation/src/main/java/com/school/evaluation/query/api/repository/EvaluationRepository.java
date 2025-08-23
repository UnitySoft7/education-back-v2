package com.school.evaluation.query.api.repository;

import com.school.evaluation.core.model.Evaluation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EvaluationRepository extends ReactiveMongoRepository<Evaluation, String> {
    Mono<Boolean> existsEvaluationByCode ( @Param("code") String code);
    Mono<Evaluation> findEvaluationByCode ( @Param("code") String code);
    Flux<Evaluation> findAllEvaluationBySchoolYear (@Param("year") String year);
    Flux<Evaluation> findAllEvaluationByTrimester (@Param("trimester") String trimester);
//    Flux<Evaluation> findAllEvaluationByESCC (@Param("code") String code);
//    Flux<Evaluation> findAllEvaluationByESC (@Param("code") String code);
//    Flux<Evaluation> findAllEvaluationByESCCT (@Param("code") String code);
//    Flux<Evaluation> findAllEvaluationByEStudent(@Param("code") String code);
    Flux<Evaluation> findAllEvaluationByProfCodeAndTrimesterAndSchoolYear(@Param("code") String code, @Param("semestre") String semestre, @Param("year") String year);
}
