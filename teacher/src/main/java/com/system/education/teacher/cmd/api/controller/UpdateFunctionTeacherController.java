package com.system.education.teacher.cmd.api.controller;

import com.system.education.teacher.cmd.api.command.TeacherUpdatedCommand;
import com.system.education.teacher.cmd.api.command.TeacherUpdatedFunctionCommand;
import com.system.education.teacher.core.dto.MessageResponse;
import com.system.education.teacher.core.payload.TeacherPayload;
import com.system.education.teacher.core.utils.TeacherUtilsConstants;
import com.system.education.teacher.query.api.handler.TeacherEventHandler;
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
@RequestMapping(path = "/api/v1/education/teacher/update-function-teacher")
@Tag(name = "Teacher", description = "Data rest API for teacher resource")
public class UpdateFunctionTeacherController {
    private final TeacherEventHandler teacherEventHandler;
    private final TeacherPayload teacherPayload;

    /**
     * This method is used to update function for teacher
     * @param command the command containing the teacher information
     * @return the message for operation
     */
    @Operation(summary = "Update teacher")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> updateFunction (@Valid @RequestBody TeacherUpdatedFunctionCommand command) {
        return teacherPayload.isTeacherCodeExist(command.teacherCode())
                .flatMap(isExists -> {
                    if (isExists) {
                        return teacherEventHandler.updateFunction(command)
                                .flatMap(teacher -> {
                                    if (teacher != null) {
                                        return Mono.just(ResponseEntity.ok()
                                                .body(new MessageResponse(true, TeacherUtilsConstants.done)));
                                    }
                                    return Mono.just(ResponseEntity.badRequest()
                                            .body(new MessageResponse(false, TeacherUtilsConstants.operation_failed)));
                                });
                    } else {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, TeacherUtilsConstants.code_not_found)));
                    }
                });
    }
}
