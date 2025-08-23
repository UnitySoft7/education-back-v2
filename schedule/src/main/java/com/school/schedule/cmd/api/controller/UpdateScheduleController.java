package com.school.schedule.cmd.api.controller;

import com.school.schedule.cmd.api.command.ScheduleUpdatedCommand;
import com.school.schedule.core.dto.MessageResponse;
import com.school.schedule.core.payload.SchedulePayload;
import com.school.schedule.core.utils.MessageUtilsConstants;
import com.school.schedule.query.api.handler.ScheduleEventHandler;
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
@RequestMapping(path = "/api/v1/education/schedule/update-schedule")
@Tag(name = "Schedule", description = "Data rest API for Schedule resource")
public class UpdateScheduleController {

    private final ScheduleEventHandler scheduleEventHandler;
    private final SchedulePayload schedulePayload;

    @Operation(summary = "Update weekly Schedule")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody ScheduleUpdatedCommand command) {
        return scheduleEventHandler.updateSettings(command)
                .map(schedule -> ResponseEntity.ok(
                        new MessageResponse(true, MessageUtilsConstants.updated)
                ));
    }
}
