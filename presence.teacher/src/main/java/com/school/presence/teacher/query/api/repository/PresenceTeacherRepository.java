package com.school.presence.teacher.query.api.repository;

import com.school.presence.teacher.core.model.PresenceTeacher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PresenceTeacherRepository extends ReactiveMongoRepository<PresenceTeacher, String> {
    Mono<PresenceTeacher> findPresenceTeacherByCode(@Param("code") String code);
    Mono<Boolean> existsByProfAndLogCreatedAt(@Param("prof") String prof, @Param("logCreatedAt") String logCreatedAt);
}
