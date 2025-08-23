package com.school.classroom.cmd.api.controller;
import com.school.classroom.cmd.api.command.ClassroomCreatedCommand;
import com.school.classroom.core.dto.MessageResponse;
import com.school.classroom.core.payload.ClassroomPayload;
import com.school.classroom.core.utils.MessageUtilsConstants;
import com.school.classroom.query.api.handler.ClassroomEventHandler;
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
@RequestMapping(path = "/api/v1/education/classroom/create-classroom")
@Tag(name = "Classroom", description = "Data rest API for Classroom resource")
public class CreateClassroomController {
    private final ClassroomEventHandler ClassroomEventHandler;
    private final ClassroomPayload classroomPayload;

    @Operation(summary = "Create Classroom")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody ClassroomCreatedCommand command) {
        return classroomPayload.isClassroomNameExist(command.classroomName())
                .flatMap(nameExists -> {
                    if (nameExists) {
                        return Mono.just(ResponseEntity.ok(
                                new MessageResponse(false, MessageUtilsConstants.nameExist)
                        ));
                    } else {
                        return ClassroomEventHandler.create(command)
                                .map(created -> {
                                    if (created != null) {
                                        return ResponseEntity.ok(
                                                new MessageResponse(true, MessageUtilsConstants.created)
                                        );
                                    } else {
                                        return ResponseEntity.ok(
                                                new MessageResponse(false, MessageUtilsConstants.operation_failed)
                                        );
                                    }
                                });
                    }
                });
    }
}
