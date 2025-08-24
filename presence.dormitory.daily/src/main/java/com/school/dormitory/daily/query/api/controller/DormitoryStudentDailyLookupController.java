package com.school.dormitory.daily.query.api.controller;
import com.school.dormitory.daily.cmd.api.command.query.FindByCodeQuery;
import com.school.dormitory.daily.query.api.dto.AllLookupDormitoryStudentDailyResponse;
import com.school.dormitory.daily.query.api.dto.LookupDormitoryStudentDailyResponse;
import com.school.dormitory.daily.query.api.handler.DormitoryStudentDailyQueryHandler;
import com.school.dormitory.daily.query.api.repository.DormitoryStudentDailyRepository;
import com.school.dormitory.daily.query.api.response.DormitoryStudentDailyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/dormitory-student-daily/lookup-dormitory-student-daily/")
@Tag(name = "DormitoryStudentDaily", description = "Data REST API for class resource")
public class DormitoryStudentDailyLookupController {
    private final DormitoryStudentDailyQueryHandler DormitoryStudentDailyQueryHandler;
    private  final DormitoryStudentDailyRepository dormitoryStudentDailyRepository;

    @Operation(summary = "Retrieve data DormitoryStudentDaily")
    @GetMapping(path = "get-dormitory-student-dailies", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupDormitoryStudentDailyResponse>> show() {
        return DormitoryStudentDailyQueryHandler.findDormitoryStudentDailys()
                .collectList()
                .map(DormitoryStudentDaily ->
                        new AllLookupDormitoryStudentDailyResponse(true, DormitoryStudentDaily,30,4))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data DormitoryStudent Daily by Student")
    @PutMapping(path = "get-dormitory-student-dailies-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupDormitoryStudentDailyResponse>> showByStudent(@Valid @RequestBody FindByCodeQuery query) {
        return DormitoryStudentDailyQueryHandler.findDormitoryStudentDailyByStudent(query)
                .collectList()
                .map(dormitorys -> {
                    // Calcule la somme des présences
                    double totalPresents = dormitorys.stream()
                            .mapToDouble(DormitoryStudentDailyResponse::presents)
                            .sum();
                    // Calcule la somme des absences
                    double totalAbsents = dormitorys.stream()
                            .mapToDouble(DormitoryStudentDailyResponse::absents)
                            .sum();

                    // Construit la réponse
                    AllLookupDormitoryStudentDailyResponse response = new AllLookupDormitoryStudentDailyResponse(
                            true,
                            dormitorys,
                            totalPresents,
                            totalAbsents
                    );

                    return ResponseEntity.ok(response);
                })
                .onErrorResume(ex -> {
                    ex.printStackTrace(); // À retirer en prod
                    return Mono.just(
                            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new AllLookupDormitoryStudentDailyResponse(false, List.of(), 0, 0))
                    );
                });
    }

    @Operation(summary = "Retrieve data DormitoryStudentDaily by dormitoryStudentDailyCode")
    @PutMapping(path = "get-dormitoryStudentDaily-by-dormitory-student-daily-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getDormitoryStudentDailyByDormitoryStudentDailyCode(@Valid@RequestBody FindByCodeQuery query) {
        return DormitoryStudentDailyQueryHandler.findDormitoryStudentDailyByDormitoryStudentDailyCode(query.code())
                .map(Classroom ->
                        new LookupDormitoryStudentDailyResponse(true, Classroom))
                .map(ResponseEntity::ok);
    }
}
