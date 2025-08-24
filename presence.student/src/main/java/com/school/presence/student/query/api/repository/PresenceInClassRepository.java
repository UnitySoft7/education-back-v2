package com.school.presence.student.query.api.repository;

import com.school.presence.student.core.model.PresenceInClass;
import com.school.presence.student.query.api.response.PresenceQueryResponse;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PresenceInClassRepository extends ReactiveMongoRepository<PresenceInClass, String> {
    Mono<PresenceInClass> findPresenceInClassByPresenceInClassCode(@Param("code") String code);

//    Flux<PresenceInClass> findPresenceInClassByESCS(@Param("code") String code);


    @Aggregation(pipeline = {"{ $unwind: '$presence' }", "{ $group: { " + "   _id: '$presence.studentCode', " + "   presents: { $sum: { $cond: [ { $eq: ['$presence.status', 'PRESENT'] }, 1, 0 ] } }, " + "   absents: { $sum: { $cond: [ { $eq: ['$presence.status', 'ABSENT'] }, 1, 0 ] } } " + "} }", "{ $project: { escs: '$_id', presents: 1, absents: 1, _id: 0 } }"})
    Flux<PresenceQueryResponse> countByEscs(@Param("code") String code);

}
