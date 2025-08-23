package com.school.schedule.query.api.controller;

import com.school.schedule.cmd.api.query.FindByCodeQuery;
import com.school.schedule.core.common.Days;
import com.school.schedule.query.api.dto.*;
import com.school.schedule.query.api.handler.ScheduleQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/schedule/lookup-schedule/")
@Tag(name = "Schedule", description = "Data REST API for class resource")
public class SchedulesLookupController {
    private final ScheduleQueryHandler ScheduleQueryHandler;

    @Operation(summary = "Retrieve data Schedule")
    @GetMapping(path = "get-schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupScheduleResponse>> show() {
        return ScheduleQueryHandler.findSchedules()
                .collectList()
                .map(Schedule ->
                        new AllLookupScheduleResponse (true, Schedule))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data Schedule")
    @PutMapping(path = "get-all-schedules-by-class", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupScheduleResponse>> showAllByclass(@Valid @RequestBody FindByCodeQuery query) {
        return ScheduleQueryHandler.findAllScheduleByClassCode(query)
                .collectList()
                .map(Schedule ->
                        new AllLookupScheduleResponse(true, Schedule))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data schedule by scheduleCode")
    @PutMapping(path = "get-schedule-by-schedule-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getScheduleByScheduleCode(@Valid@RequestBody FindByCodeQuery query) {
        return ScheduleQueryHandler.findScheduleByScheduleCode (query.code())
                .map(schedule ->
                        new LookupScheduleResponse (true, schedule))
                .map(ResponseEntity::ok);
    }
}
