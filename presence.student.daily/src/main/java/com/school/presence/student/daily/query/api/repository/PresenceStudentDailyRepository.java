package com.school.presence.student.daily.query.api.repository;

import com.school.presence.student.daily.core.model.PresenceStudentDaily;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PresenceStudentDailyRepository extends ReactiveMongoRepository<PresenceStudentDaily, String> {
    Mono<Boolean> existsPresenceStudentDailyByCode ( @Param("code") String code);
    Mono<PresenceStudentDaily> findPresenceStudentDailyByCode ( @Param("code") String code);
    Flux<PresenceStudentDaily> findAllPresenceByStudent(@Param("student") String student);

//    @Query("SELECT SUM(presents) FROM presence_student_daily WHERE student = :studentCode")
//    Mono<Double> countTotalPresentsByStudent(@Param("studentCode") String studentCode);
//
//    @Query("SELECT SUM(absents) FROM presence_student_daily WHERE student = :studentCode")
//    Mono<Double> countTotalAbsentsByStudent(@Param("studentCode") String studentCode);



}
