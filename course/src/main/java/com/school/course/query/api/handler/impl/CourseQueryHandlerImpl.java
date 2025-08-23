package com.school.course.query.api.handler.impl;

import com.school.course.core.model.Course;
import com.school.course.query.api.handler.CourseQueryHandler;
import com.school.course.query.api.repository.CourseRepository;
import com.school.course.query.api.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseQueryHandlerImpl implements CourseQueryHandler {
    private final CourseRepository courseRepository;

    @Override
    public Flux<CourseResponse> findCourses() {
        return courseRepository.findAll()
                .map(this::getCourse);
    }

    @Override
    public Mono<CourseResponse> findCourseById(String courseId) {
        return courseRepository.findById(courseId)
                .map(this::getCourse);
    }

    @Override
    public Mono<CourseResponse> findCourseByCourseCode(String courseCode) {
        return courseRepository.findCourseByCode(courseCode)
                .map(this::getCourse);
    }

    @Override
    public Flux<CourseResponse> findCourseByEstablishment(String establishmentCode) {
        return courseRepository.findCourseByEstablishmentCode(establishmentCode)
                .map(this::getCourse);
    }

    @Override
    public CourseResponse getCourse(Course course) {
        return new CourseResponse(
                course.getCourseId(),
                course.getName(),
                course.getCode(),
                course.getFrName(),
                course.getEnName(),
                course.getEstablishmentCode(),
                course.getEstablishmentName()
        );
    }
}
