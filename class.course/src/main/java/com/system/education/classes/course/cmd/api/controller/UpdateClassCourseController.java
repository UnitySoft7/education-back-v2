package com.system.education.classes.course.cmd.api.controller;

import com.system.education.classes.course.cmd.api.command.ClassCourseUpdatedCommand;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/class-course/update-class-course")
@Tag(name = "Class-Course", description = "Data rest API for class-course resource")
public class UpdateClassCourseController {
    private final ClassCourseEventHandler classCourseEventHandler;
    private final ClassCoursePayload classCoursePayload;

    /**
     * This method is used to update a new class-course
     * @param command the command containing the class-course information
     * @return the message for operation
     */
    @Operation(summary = "Update class-course")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update (@Valid @RequestBody ClassCourseUpdatedCommand command) {
        return classCoursePayload.isClassCourseExist(command.classCourseCode())
                .flatMap(isExists -> {
                    if (isExists) {
//                        return classCoursePayload.verifyCourseCode(command.courseCode())
//                                .flatMap(lookupCourseResponse -> {
//                                    if (lookupCourseResponse.success()) {
                        return classCoursePayload.updateException(command).flatMap(exist -> {
                            if (!exist) {
                                        return classCourseEventHandler.update(command)
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
                    } else {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, ClassCourseUtilsConstants.code_not_found)));
                    }
                });
    }
}
