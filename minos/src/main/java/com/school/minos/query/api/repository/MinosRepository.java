package com.school.minos.query.api.repository;

import com.school.minos.core.model.Minos;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MinosRepository extends ReactiveMongoRepository<Minos, String> {
    Mono<Boolean> existsMinosByMinosCode(@Param("code") String code);
    Mono<Minos> findMinosByMinosCode ( @Param("code") String code);
//    Mono<Minos>
}
