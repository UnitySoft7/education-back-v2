package com.school.schedule.query.api.repository;

import com.school.schedule.core.model.Schedule;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ScheduleRepository extends ReactiveMongoRepository<Schedule, String> {
    Mono<Boolean> existsScheduleByCode ( @Param("code") String code);
    Mono<Schedule> findScheduleByCode ( @Param("code") String code);
    Flux<Schedule> findAllScheduleByClassroomCode(@Param("code") String code);






//    Mono<Schedule> findScheduleByESC ( @Param("code") String code);
//    Mono<Schedule> findByDayAndEstablishmentCodeAndSectionCode( @Param("day") String day, @Param("code") String code, @Param("sectionCode") String sectionCode);
//    Mono<Schedule> findScheduleByCodeAndIndex1 ( @Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex2 ( @Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex3 ( @Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex4 ( @Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex5 ( @Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex6 ( @Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex7 ( @Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex8 ( @Param("code") String code, @Param("index") String index);

//    Mono<Schedule> findScheduleByCodeAndIndex1(@Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex2(@Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex3(@Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex4(@Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex5(@Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex6(@Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex7(@Param("code") String code, @Param("index") String index);
//    Mono<Schedule> findScheduleByCodeAndIndex8(@Param("code") String code, @Param("index") String index);



}
