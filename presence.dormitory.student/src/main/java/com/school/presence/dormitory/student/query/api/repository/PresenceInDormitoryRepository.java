package com.school.presence.dormitory.student.query.api.repository;

import com.school.presence.dormitory.student.core.model.PresenceInDormitory;
import com.school.presence.dormitory.student.query.api.response.PresenceQueryResponse;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PresenceInDormitoryRepository extends ReactiveMongoRepository<PresenceInDormitory, String> {
    Mono<PresenceInDormitory> findPresenceInDormitoryByPresenceInDormitoryCode(@Param("code") String code);
    @Aggregation(pipeline = {"{ $unwind: '$presence' }", "{ $group: { " + "   _id: '$presence.student', " + "   presents: { $sum: { $cond: [ { $eq: ['$presence.status', 'PRESENT'] }, 1, 0 ] } }, " + "   absents: { $sum: { $cond: [ { $eq: ['$presence.status', 'ABSENT'] }, 1, 0 ] } } " + "} }", "{ $project: { escs: '$_id', presents: 1, absents: 1, _id: 0 } }"})
    Flux<PresenceQueryResponse> countByEscs(@Param("code") String code);
}
