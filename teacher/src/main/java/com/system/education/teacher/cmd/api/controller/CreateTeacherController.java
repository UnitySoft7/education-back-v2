package com.system.education.teacher.cmd.api.controller;

import com.system.education.teacher.cmd.api.command.TeacherCreatedCommand;
import com.system.education.teacher.core.dto.FieldsValidatorResponse;
import com.system.education.teacher.core.dto.MessageResponse;
import com.system.education.teacher.core.payload.TeacherPayload;
import com.system.education.teacher.core.utils.TeacherUtilsConstants;
import com.system.education.teacher.query.api.handler.TeacherEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/teacher/create-teacher")
@Tag(name = "Teacher", description = "Data rest API for teacher resource")
public class CreateTeacherController {
    private final TeacherEventHandler teacherEventHandler;
    private final TeacherPayload teacherPayload;

    /**
     * This method is used to create a new teacher
     * @param command the command containing the teacher information
     * @return the message for operation
     */
    @Operation(summary = "Create teacher")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> create (@Valid @RequestBody TeacherCreatedCommand command) {
        return teacherPayload.verifyPassword(command.password(), command.verifyPassword())
                .flatMap(messageResponse -> {
                    if (!messageResponse.success()) {
                        Map<String, String> errorValidator = new HashMap<>();
                        errorValidator.put("verifyPassword", TeacherUtilsConstants.verify_password);
                        return Mono.just(new ResponseEntity<>(new FieldsValidatorResponse(false,
                                errorValidator), HttpStatus.BAD_REQUEST));
                    }
                    return teacherEventHandler.create(command)
                            .flatMap(teacher -> {
                                if (teacher != null) {
                                    return Mono.just(ResponseEntity.ok()
                                            .body(new MessageResponse(true, TeacherUtilsConstants.done)));
                                }
                                return Mono.just(ResponseEntity.badRequest()
                                        .body(new MessageResponse(false, TeacherUtilsConstants.operation_failed)));
                            });
                });

    }
}
