package com.system.education.course.teacher.cmd.api.controller;

import com.system.education.course.teacher.cmd.api.command.CourseTeacherCreatedCommand;
import com.system.education.course.teacher.core.dto.MessageResponse;
import com.system.education.course.teacher.core.payload.CourseTeacherPayload;
import com.system.education.course.teacher.core.utils.EstablishmentSectionClassUtilsConstants;
import com.system.education.course.teacher.query.api.handler.CourseTeacherEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/course-teacher/create-course-teacher")
@Tag(name = "Course-Teacher", description = "Data rest API for course-teacher resource")
public class CreateCourseTeacherController {
    private final CourseTeacherEventHandler courseTeacherEventHandler;
    private final CourseTeacherPayload courseTeacherPayload;

    /**
     * This method is used to create a new course-teacher
     * @param command the command containing the course-teacher information
     * @return the message for operation
     */
    @Operation(summary = "Create course-teacher")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody CourseTeacherCreatedCommand command) {
//        return courseTeacherPayload.verifyEstablishmentSectionClassCourseCode(command.courseTeacherCode())
//                .flatMap(lookupEstablishmentSectionClassCourseResponse -> {
//                    if (lookupEstablishmentSectionClassCourseResponse.success()) {
//                        return courseTeacherPayload.verifyTeacherCode(command.teacherCode())
//                                .flatMap(lookupTeacherResponse -> {
//                                    if (lookupTeacherResponse.success()) {
                                        return courseTeacherEventHandler.create(command)
                                                .flatMap(establishmentSectionClassCourse -> {
                                                    if (establishmentSectionClassCourse != null) {
                                                        return Mono.just(ResponseEntity.ok()
                                                                .body(new MessageResponse(true, EstablishmentSectionClassUtilsConstants.done)));
                                                    }
                                                    return Mono.just(ResponseEntity.badRequest()
                                                            .body(new MessageResponse(false, EstablishmentSectionClassUtilsConstants.operation_failed)));
                                                });
//                                    } else {
//                                        return Mono.just(ResponseEntity.badRequest()
//                                                .body(new MessageResponse(false, EstablishmentSectionClassUtilsConstants.teacher_not_found)));
//                                    }
//                                });
//                    } else {
//                        return Mono.just(ResponseEntity.badRequest()
//                                .body(new MessageResponse(false, EstablishmentSectionClassUtilsConstants.establishment_section_class_course_not_found)));
//                    }
//                });
    }
}
