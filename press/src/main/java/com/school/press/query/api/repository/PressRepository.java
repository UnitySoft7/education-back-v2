package com.school.press.query.api.repository;

import com.school.press.core.model.Press;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PressRepository extends ReactiveMongoRepository<Press, String> {
    Mono<Press> findPressByCode ( @Param("code") String code);
    Mono<Press> findPressBySigle(@Param("name") String name);
}
