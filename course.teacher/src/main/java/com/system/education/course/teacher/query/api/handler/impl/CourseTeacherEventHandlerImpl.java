package com.system.education.course.teacher.query.api.handler.impl;

import com.system.education.course.teacher.cmd.api.command.CourseTeacherCreatedCommand;
import com.system.education.course.teacher.cmd.api.command.CourseTeacherUpdatedCommand;
import com.system.education.course.teacher.core.common.LogCreated;
import com.system.education.course.teacher.core.model.CourseTeacher;
import com.system.education.course.teacher.core.payload.CourseTeacherPayload;
import com.system.education.course.teacher.query.api.handler.CourseTeacherEventHandler;
import com.system.education.course.teacher.query.api.repository.CourseTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CourseTeacherEventHandlerImpl implements CourseTeacherEventHandler {
    private final CourseTeacherRepository courseTeacherRepository;
    private final CourseTeacherPayload courseTeacherPayload;

    @Override
    public Mono<CourseTeacher> create(CourseTeacherCreatedCommand command) {
        return courseTeacherPayload.getCode()
                .flatMap(code -> {
                    CourseTeacher courseTeacher = CourseTeacher.builder()
                            .courseTeacherId(UUID.randomUUID().toString())
                            .courseTeacherCode(code)
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .sectionCode(command.sectionCode())
                            .sectionName(command.sectionName())
                            .classCode(command.classCode())
                            .className(command.className())
                            .courseCode(command.courseCode())
                            .courseName(command.courseName())
                            .teacherCode(command.teacherCode())
                            .teacherName(command.teacherName())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();
                    return courseTeacherRepository.save(courseTeacher);
                });
    }

    @Override
    public Mono<CourseTeacher> update(CourseTeacherUpdatedCommand command) {
        return courseTeacherRepository.findByCourseTeacherCode(command.courseTeacherCode())
                .flatMap(courseTeacher -> {
                    courseTeacher.setTeacherCode(command.teacherCode());
                    courseTeacher.setTeacherName(command.teacherName());
                    return courseTeacherRepository.save(courseTeacher);
                });
    }
}
