package com.system.education.classes.course.cmd.api.controller;

import com.system.education.classes.course.cmd.api.command.ClassCourseCreatedCommand;
import com.system.education.classes.course.core.dto.MessageResponse;
import com.system.education.classes.course.core.payload.ClassCoursePayload;
import com.system.education.classes.course.core.utils.ClassCourseUtilsConstants;
import com.system.education.classes.course.query.api.handler.ClassCourseEventHandler;
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
@RequestMapping(path = "/api/v1/education/class-course/create-class-course")
@Tag(name = "Class-Course", description = "Data rest API for class-course resource")
public class CreateClassCourseController {
    private final ClassCourseEventHandler classCourseEventHandler;
    private final ClassCoursePayload classCoursePayload;

    /**
     * This method is used to create a new class-course
     * @param command the command containing the class-course information
     * @return the message for operation
     */
    @Operation(summary = "Create class-course")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody ClassCourseCreatedCommand command) {
//        return classCoursePayload.verifyEstablishmentSectionClassCode(command.establishmentSectionClassCode())
//                .flatMap(lookupEstablishmentSectionClassResponse -> {
//                    if (lookupEstablishmentSectionClassResponse.success()) {
//                        return classCoursePayload.verifyCourseCode(command.courseCode())
//                                .flatMap(lookupCourseResponse -> {
//                                    if (lookupCourseResponse.success()) {
        return classCoursePayload.createException(command).flatMap(exist -> {
                    if (!exist) {
                        return classCourseEventHandler.create(command)
                                .flatMap(establishmentSectionClassCourse -> {
                                    if (establishmentSectionClassCourse != null) {
                                        return Mono.just(ResponseEntity.ok()
                                                .body(new MessageResponse(true, ClassCourseUtilsConstants.done)));
                                    }
                                    return Mono.just(ResponseEntity.badRequest()
                                            .body(new MessageResponse(false, ClassCourseUtilsConstants.operation_failed)));
                                });
                    } else {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, ClassCourseUtilsConstants.class_course_already_exist)));
                    }
                });
//                                    } else {
//                                        return Mono.just(ResponseEntity.badRequest()
//                                                .body(new MessageResponse(false, ClassCourseUtilsConstants.course_not_found)));
//                                    }
//                                });
//                    } else {
//                        return Mono.just(ResponseEntity.badRequest()
//                                .body(new MessageResponse(false, ClassCourseUtilsConstants.establishment_section_class_not_found)));
//                    }
//                });
    }
}
