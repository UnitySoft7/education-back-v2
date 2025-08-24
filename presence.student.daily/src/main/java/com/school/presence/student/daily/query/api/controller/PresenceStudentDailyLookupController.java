package com.school.presence.student.daily.query.api.controller;
import com.school.presence.student.daily.cmd.api.command.query.FindByCodeQuery;
import com.school.presence.student.daily.query.api.dto.AllLookupPresenceStudentDailyResponse;
import com.school.presence.student.daily.query.api.dto.LookupPresenceStudentDailyResponse;
import com.school.presence.student.daily.query.api.handler.PresenceStudentDailyQueryHandler;
import com.school.presence.student.daily.query.api.repository.PresenceStudentDailyRepository;
import com.school.presence.student.daily.query.api.response.PresenceStudentDailyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/presence-student-daily/lookup-presence-student-daily/")
@Tag(name = "Presence Student Daily", description = "Data REST API for class resource")
public class PresenceStudentDailyLookupController {
    private final PresenceStudentDailyQueryHandler PresenceStudentDailyQueryHandler;
    private  final PresenceStudentDailyRepository presenceStudentDailyRepository;


    @Operation(summary = "Retrieve today's PresenceStudentDaily data")
    @GetMapping(path = "get-presence-student-dailies-today", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPresenceStudentDailyResponse>> showToday() {
        String today = LocalDate.now().toString();
        return PresenceStudentDailyQueryHandler.findPresenceStudentDailys().filter(presenceStudentDaily -> today.equals(presenceStudentDaily.logCreatedAt())).collectList().map(filteredList -> new AllLookupPresenceStudentDailyResponse(true, filteredList, 30, 4))
                .map(ResponseEntity::ok);
    }


    @Operation(summary = "Retrieve today's PresenceStudentDaily data")
    @GetMapping(path = "get-presence-student-dailies-", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPresenceStudentDailyResponse>> show() {
        return PresenceStudentDailyQueryHandler.findPresenceStudentDailys().collectList().map(filteredList -> new AllLookupPresenceStudentDailyResponse(true, filteredList, 30, 4))
                .map(ResponseEntity::ok);
    }


    @Operation(summary = "Retrieve data PresenceStudent Daily by Student")
    @PutMapping(path = "get-presence-student-dailies-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPresenceStudentDailyResponse>> showByStudent(@Valid @RequestBody FindByCodeQuery query) {
        return PresenceStudentDailyQueryHandler.findPresenceStudentDailyByStudent(query)
                .collectList()
                .map(presences -> {
                    // Calcule la somme des présences
                    double totalPresents = presences.stream()
                            .mapToDouble(PresenceStudentDailyResponse::presents)
                            .sum();
                    // Calcule la somme des absences
                    double totalAbsents = presences.stream()
                            .mapToDouble(PresenceStudentDailyResponse::absents)
                            .sum();

                    // Construit la réponse
                    AllLookupPresenceStudentDailyResponse response = new AllLookupPresenceStudentDailyResponse(
                            true,
                            presences,
                            totalPresents,
                            totalAbsents
                    );

                    return ResponseEntity.ok(response);
                })
                .onErrorResume(ex -> {
                    ex.printStackTrace(); // À retirer en prod
                    return Mono.just(
                            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new AllLookupPresenceStudentDailyResponse(false, List.of(), 0, 0))
                    );
                });
    }

    @Operation(summary = "Retrieve data PresenceStudentDaily by presenceStudentDailyCode")
    @PutMapping(path = "get-presenceStudentDaily-by-presence-student-daily-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getPresenceStudentDailyByPresenceStudentDailyCode(@Valid@RequestBody FindByCodeQuery query) {
        return PresenceStudentDailyQueryHandler.findPresenceStudentDailyByPresenceStudentDailyCode(query.code())
                .map(Classroom ->
                        new LookupPresenceStudentDailyResponse(true, Classroom))
                .map(ResponseEntity::ok);
    }
}