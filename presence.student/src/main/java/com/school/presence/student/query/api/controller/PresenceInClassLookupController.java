package com.school.presence.student.query.api.controller;

import com.school.presence.student.core.common.PresenceSta;
import com.school.presence.student.query.api.dto.AllLookupPresenceInClassResponse;
import com.school.presence.student.query.api.dto.LookupPresenceInClassResponse;
import com.school.presence.student.query.api.dto.LookupPresenceStaResponse;
import com.school.presence.student.query.api.handler.PresenceInClassQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/presence-student/lookup-presence-student/")
@Tag(name = "Presence Student", description = "Data REST API for class resource")
public class PresenceInClassLookupController {
    private final PresenceInClassQueryHandler presenceInClassQueryHandler;

    @Operation(summary = "Retrieve data PresenceInClass")
    @GetMapping(path = "get-presencestudents", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPresenceInClassResponse>> show() {
        return presenceInClassQueryHandler.findPresenceInClasss()
                .collectList()
                .map(PresenceInClass ->
                        new AllLookupPresenceInClassResponse(true, PresenceInClass))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data PresenceInClass today")
    @GetMapping(path = "get-presence-students-today", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPresenceInClassResponse>> showToday() {
        LocalDate date= LocalDate.now();
        String da = String.valueOf(date);
        return presenceInClassQueryHandler.findPresenceInClasss()
                .filter(presenceInClasss->da.equals(presenceInClasss.logCreatedAt()))
                .collectList()
                .map(PresenceInClass ->
                        new AllLookupPresenceInClassResponse(true, PresenceInClass))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data presenceInClass by presenceInClassCode")
    @GetMapping(path = "get-presenceInClass-by-presence-student-code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getPresenceInClassByPresenceInClassCode(@PathVariable("code") String code) {
        return presenceInClassQueryHandler.findPresenceInClassByPresenceInClassCode(code)
                .map(presenceInClass ->
                        new LookupPresenceInClassResponse(true, presenceInClass))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data presence Sta")
    @GetMapping(path = "/get-student-presence-and-its-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupPresenceStaResponse>> getPresenceSta() {
        List<String> statuses = List.of(
                PresenceSta.isAbsent(),
                PresenceSta.isPresent()
        );
        LookupPresenceStaResponse response = new LookupPresenceStaResponse(true, statuses);
        return Mono.just(ResponseEntity.ok(response));
    }
}
