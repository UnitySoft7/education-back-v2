package com.system.education.student.cmd.api.controller;

import com.system.education.student.cmd.api.command.StudentCreatedCommand;
import com.system.education.student.core.dto.MessageResponse;
import com.system.education.student.core.utils.StudentUtilsConstants;
import com.system.education.student.query.api.handler.StudentEventHandler;
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
@RequestMapping(path = "/api/v1/education/student/create-student")
@Tag(name = "Student", description = "Data rest API for student resource")
public class CreateStudentController {
    private final StudentEventHandler studentEventHandler;

    /**
     * This method is used to create a new student
     * @param command the command containing the student information
     * @return the message for operation
     */
    @Operation(summary = "Create student")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public Mono<ResponseEntity<MessageResponse>> create (@Valid StudentCreatedCommand command) {
        return studentEventHandler.create(command)
                .flatMap(student -> {
                    if (student != null) {
                        return Mono.just(ResponseEntity.ok()
                                .body(new MessageResponse(true, StudentUtilsConstants.done)));
                    }
                    return Mono.just(ResponseEntity.badRequest()
                            .body(new MessageResponse(false, StudentUtilsConstants.operation_failed)));
                });
    }
}
