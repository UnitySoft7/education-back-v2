package com.school.dormitory.query.api.repository;

import com.school.dormitory.core.model.Dormitory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DormitoryRepository extends ReactiveMongoRepository<Dormitory, String> {
    Mono<Boolean> existsDormitoryByCode ( @Param("code") String code);
    Mono<Dormitory> findDormitoryByCode ( @Param("code") String code);
    Mono<Dormitory>findDormitoryByName( @Param("name") String name);
    Mono<Boolean> existsDormitoryByName ( @Param("name") String name);
}
