package com.school.course.query.api.handler.impl;

import com.school.course.cmd.api.command.CourseCreatedCommand;
import com.school.course.cmd.api.command.CourseUpdatedCommand;
import com.school.course.core.model.Course;
import com.school.course.core.payload.CoursePayload;
import com.school.course.query.api.handler.CourseEventHandler;
import com.school.course.query.api.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseEventHandlerImpl implements CourseEventHandler {
    private final CourseRepository courseRepository;
    private final CoursePayload coursePayload;

    @Override
    public Mono<Course> create(CourseCreatedCommand command) {
        return coursePayload.getCode().flatMap(code -> {
            Course value = Course.builder()
                    .courseId(UUID.randomUUID().toString())
                    .name(command.courseName())
                    .code(code)
                    .frName(command.frName())
                    .enName(command.enName())
                    .establishmentCode(command.establishmentCode())
                    .establishmentName(command.establishmentName())
                    .build();
            return courseRepository.save(value);
        });
    }

    @Override
    public Mono<Course> update(CourseUpdatedCommand command) {
        return courseRepository.findCourseByCode(command.courseCode())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Course not found")))
                .flatMap(existingCourse ->
                        courseRepository.findCourseByName(command.courseName())
                                .filter(other -> !other.getCode().equals(existingCourse.getCode()))
                                .flatMap(conflict -> Mono.error(new IllegalArgumentException("Course name already in use")))
                                .then(Mono.defer(() -> {
                                    existingCourse.setName(command.courseName());
                                    existingCourse.setFrName(command.frName());
                                    existingCourse.setEnName(command.enName());
                                    return courseRepository.save(existingCourse);
                                }))
                );
    }
}
