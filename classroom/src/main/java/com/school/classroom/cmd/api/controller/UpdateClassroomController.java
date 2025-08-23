package com.school.classroom.cmd.api.controller;

import com.school.classroom.cmd.api.command.ClassroomUpdatedCommand;
import com.school.classroom.core.dto.MessageResponse;
import com.school.classroom.core.model.Classroom;
import com.school.classroom.core.payload.ClassroomPayload;
import com.school.classroom.core.utils.MessageUtilsConstants;
import com.school.classroom.query.api.handler.ClassroomEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(path = "/api/v1/education/classroom/update-classroom")
@Tag(name = "Classroom", description = "Data rest API for update resource")
public class UpdateClassroomController {
    private final ClassroomEventHandler classroomEventHandler;
    private final ClassroomPayload classroomPayload;

    @Operation(summary = "update")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@RequestBody ClassroomUpdatedCommand command) {
        return classroomPayload.isClassroomCodeExist(command.classroomCode())
                .flatMap(exist -> {
                    if (exist) {
                        Mono<Classroom> updateMono = classroomEventHandler.update(command);
                        return updateMono.flatMap(update -> {
                            if (update != null) {
                                return Mono.just(ResponseEntity.ok()
                                        .body(new MessageResponse(true, MessageUtilsConstants.updated)));
                            }
                            return Mono.just(ResponseEntity.ok()
                                    .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                        });
                    }
                    return Mono.just(ResponseEntity.ok()
                            .body(new MessageResponse(false, MessageUtilsConstants.code_not_found)));
                });
    }
}

