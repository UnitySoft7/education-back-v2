package com.school.course.query.api.repository;

import com.school.course.core.model.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course, String> {
    Mono<Boolean> existsCourseByCode ( @Param("code") String code);
    Mono<Course> findCourseByCode ( @Param("code") String code);
    Mono<Course>findCourseByName( @Param("name") String name);
    Mono<Boolean> existsCourseByName ( @Param("name") String name);
    Flux<Course> findCourseByEstablishmentCode (@Param("establishmentCode") String code);
}
