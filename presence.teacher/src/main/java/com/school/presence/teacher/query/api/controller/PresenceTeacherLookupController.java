package com.school.presence.teacher.query.api.controller;

import com.school.presence.teacher.query.api.dto.AllLookupPresenceTeacherResponse;
import com.school.presence.teacher.query.api.dto.LookupPresenceTeacherResponse;
import com.school.presence.teacher.query.api.handler.PresenceTeacherQueryHandler;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/presence-teacher/lookup-presence-teacher/")
@Tag(name = "PresenceTeacher", description = "Data REST API for class resource")
public class PresenceTeacherLookupController {
    private final PresenceTeacherQueryHandler PresenceTeacherQueryHandler;

    @Operation(summary = "Retrieve data PresenceTeacher")
    @GetMapping(path = "get-presence-teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPresenceTeacherResponse>> show() {
        return PresenceTeacherQueryHandler.findPresenceTeachers()
                .collectList()
                .map(PresenceTeacher ->
                        new AllLookupPresenceTeacherResponse(true, PresenceTeacher))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve today's PresenceTeacher data")
    @GetMapping(path = "get-presence-teachers-today", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPresenceTeacherResponse>> showTeacher() {
        LocalDate today = LocalDate.now();
        String todays = String.valueOf(today);
        return PresenceTeacherQueryHandler.findPresenceTeachers().filter(presenceTeacher -> todays.equals(presenceTeacher.logCreatedAt())) // Assure-toi que getDate() renvoie un LocalDate
                .collectList().map(filteredList -> new AllLookupPresenceTeacherResponse(true, filteredList)).map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data presenceTeacher by code")
    @GetMapping(path = "get-presence-teacher-by-presence-teacher-code-today/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getPresenceTeacherByPresenceTeacherCodetoday(@PathVariable("code") String code) {
        LocalDate today = LocalDate.now();
        String todays = String.valueOf(today);
        return PresenceTeacherQueryHandler.findPresenceTeacherByPresenceTeacherCode(code)
                .filter(
                        presenceTeacher
                        -> todays.equals(presenceTeacher.logCreatedAt())).
                map(presenceTeacher -> new LookupPresenceTeacherResponse(true, presenceTeacher)).map(ResponseEntity::ok);

    }

    @Operation(summary = "Retrieve data presenceTeacher by code")
    @GetMapping(path = "get-presence-teacher-by-presence-teacher-code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getPresenceTeacherByPresenceTeacherCode(@PathVariable("code") String code) {
        return PresenceTeacherQueryHandler.findPresenceTeacherByPresenceTeacherCode(code)
                .map(presenceTeacher ->
                        new LookupPresenceTeacherResponse(true, presenceTeacher))
                .map(ResponseEntity::ok);
    }

}
