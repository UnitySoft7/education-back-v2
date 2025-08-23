package com.system.education.classes.course.query.api.handler.impl;

import com.system.education.classes.course.cmd.api.command.ClassCourseCreatedCommand;
import com.system.education.classes.course.cmd.api.command.ClassCourseUpdatedCommand;
import com.system.education.classes.course.core.common.LogCreated;
import com.system.education.classes.course.core.model.ClassCourse;
import com.system.education.classes.course.core.payload.ClassCoursePayload;
import com.system.education.classes.course.query.api.handler.ClassCourseEventHandler;
import com.system.education.classes.course.query.api.repository.ClassCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClassCourseEventHandlerImpl implements ClassCourseEventHandler {
    private final ClassCourseRepository classCourseRepository;
    private final ClassCoursePayload classCoursePayload;

    @Override
    public Mono<ClassCourse> create(ClassCourseCreatedCommand command) {
        return classCoursePayload.getCode()
                .flatMap(code -> {
                    ClassCourse classCourse = ClassCourse.builder()
                            .classCourseId(UUID.randomUUID().toString())
                            .classCourseCode(code)
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .sectionCode(command.sectionCode())
                            .sectionName(command.sectionName())
                            .classCode(command.classCode())
                            .className(command.className())
                            .courseCode(command.courseCode())
                            .courseName(command.courseName())
                            .ponderation(command.ponderation())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();
                    return classCourseRepository.save(classCourse);
                });
    }

    @Override
    public Mono<ClassCourse> update(ClassCourseUpdatedCommand command) {
        return classCourseRepository.findByClassCourseCode(command.classCourseCode())
                .flatMap(classCourse -> {
                    classCourse.setCourseCode(command.courseCode());
                    classCourse.setCourseName(command.courseName());
                    classCourse.setPonderation(command.ponderation());
                    return classCourseRepository.save(classCourse);
                });
    }
}
