package com.system.education.parent.query.api.repository;

import com.system.education.parent.core.model.Parent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ParentRepository extends ReactiveMongoRepository<Parent, String> {
    Mono<Parent> findByParentCode(@Param("parentCode") String parentCode);
    Mono<Boolean> existsByParentCode(@Param("parentCode") String parentCode);
}
