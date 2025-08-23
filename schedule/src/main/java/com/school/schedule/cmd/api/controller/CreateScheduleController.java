package com.school.schedule.cmd.api.controller;

import com.school.schedule.cmd.api.command.ScheduleCreatedCommand;
import com.school.schedule.core.dto.MessageResponse;
import com.school.schedule.core.model.Schedule;
import com.school.schedule.core.payload.SchedulePayload;
import com.school.schedule.core.payload.impl.SchedulePayloadImpl;
import com.school.schedule.core.utils.MessageUtilsConstants;
import com.school.schedule.query.api.handler.ScheduleEventHandler;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/schedule/create-schedule")
@Tag(name = "Schedule", description = "Data rest API for Schedule resource")
public class CreateScheduleController {

    private final ScheduleEventHandler scheduleEventHandler;
    private final SchedulePayload schedulePayload;

    @Operation(summary = "Create weekly Schedule")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody ScheduleCreatedCommand command) {
        return scheduleEventHandler.create(command)
                .map(schedule -> ResponseEntity.ok(
                        new MessageResponse(true, MessageUtilsConstants.created)
                ));
    }
}
