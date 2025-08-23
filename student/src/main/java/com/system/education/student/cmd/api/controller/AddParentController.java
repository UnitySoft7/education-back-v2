package com.system.education.student.cmd.api.controller;

import com.system.education.student.cmd.api.command.AddParentCommand;
import com.system.education.student.cmd.api.command.StudentUpdatedCommand;
import com.system.education.student.core.dto.MessageResponse;
import com.system.education.student.core.payload.StudentPayload;
import com.system.education.student.core.utils.StudentUtilsConstants;
import com.system.education.student.query.api.handler.StudentEventHandler;
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
@RequestMapping(path = "/api/v1/education/student/add-parent")
@Tag(name = "Student", description = "Data rest API for student resource")
public class AddParentController {
    private final StudentEventHandler studentEventHandler;
    private final StudentPayload studentPayload;

    /**
     * This method is used to add a parent to a student.
     * @param command the command containing the student information
     * @return the message for operation
     */
    @Operation(summary = "Add parent to student")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> add (@Valid @RequestBody AddParentCommand command) {
        return studentPayload.isStudentCodeExist(command.studentCode())
                .flatMap(isExists -> {
                    if (isExists) {
                        return studentEventHandler.addParent(command)
                                .flatMap(student -> {
                                    if (student != null) {
                                        return Mono.just(ResponseEntity.ok()
                                                .body(new MessageResponse(true, StudentUtilsConstants.done)));
                                    }
                                    return Mono.just(ResponseEntity.badRequest()
                                            .body(new MessageResponse(false, StudentUtilsConstants.operation_failed)));
                                });
                    } else {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, StudentUtilsConstants.code_not_found)));
                    }
                });
    }
}
