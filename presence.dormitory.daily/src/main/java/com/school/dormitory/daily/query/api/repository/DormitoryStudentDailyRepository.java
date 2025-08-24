package com.school.dormitory.daily.query.api.repository;

import com.school.dormitory.daily.core.model.DormitoryStudentDaily;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DormitoryStudentDailyRepository extends ReactiveMongoRepository<DormitoryStudentDaily, String> {
    Mono<Boolean> existsDormitoryStudentDailyByCode ( @Param("code") String code);
    Mono<DormitoryStudentDaily> findDormitoryStudentDailyByCode ( @Param("code") String code);
    Flux<DormitoryStudentDaily> findAllDormitoryByStudent(@Param("student") String student);

//    @Query("SELECT SUM(presents) FROM dormitory_student_daily WHERE student = :studentCode")
//    Mono<Double> countTotalPresentsByStudent(@Param("studentCode") String studentCode);
//
//    @Query("SELECT SUM(absents) FROM dormitory_student_daily WHERE student = :studentCode")
//    Mono<Double> countTotalAbsentsByStudent(@Param("studentCode") String studentCode);



}
