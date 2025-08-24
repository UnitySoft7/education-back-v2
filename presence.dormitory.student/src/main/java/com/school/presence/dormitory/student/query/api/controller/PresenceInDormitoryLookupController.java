package com.school.presence.dormitory.student.query.api.controller;

import com.school.presence.dormitory.student.core.common.PresenceSta;
import com.school.presence.dormitory.student.query.api.dto.AllLookupPresenceInDormitoryResponse;
import com.school.presence.dormitory.student.query.api.dto.LookupPresenceInDormitoryResponse;
import com.school.presence.dormitory.student.query.api.dto.LookupPresenceStaResponse;
import com.school.presence.dormitory.student.query.api.handler.PresenceInDormitoryQueryHandler;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/presence-dormitory-student/lookup-presence-dormitory-student/")
@Tag(name = "Presence Dormitory Student", description = "Data REST API for class resource")
public class PresenceInDormitoryLookupController {
    private final PresenceInDormitoryQueryHandler presenceInDormitoryQueryHandler;

    @Operation(summary = "Retrieve data PresenceInDormitory")
    @GetMapping(path = "get-presence-dormitory-students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupPresenceInDormitoryResponse>> show() {
        return presenceInDormitoryQueryHandler.findPresenceInDormitorys()
                .collectList()
                .map(PresenceInDormitory ->
                        new AllLookupPresenceInDormitoryResponse(true, PresenceInDormitory))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data presenceInDormitory by presenceInDormitoryCode")
    @GetMapping(path = "get-presence-dormitory-dormitory-by-presence-dormitory-student-code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getPresenceInDormitoryByPresenceInDormitoryCode(@PathVariable("code") String code) {
        return presenceInDormitoryQueryHandler.findPresenceInDormitoryByPresenceInDormitoryCode(code)
                .map(presenceInDormitory ->
                        new LookupPresenceInDormitoryResponse(true, presenceInDormitory))
                .map(ResponseEntity::ok);
    }


//    @Operation(summary = "Retrieve data presenceInDormitory by presenceInDormitoryCode")
//    @GetMapping(path = "get-presenceInDormitory-by-presence-student-code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<ResponseEntity<?>> getPresenceByESCS(@PathVariable("code") String code) {
//        return presenceInDormitoryQueryHandler.findByESCS(code)
//                .map(presenceQueryResponse ->
//                        new LookupPresenceInDormitoryResponse(true, presenceQueryResponse))
//                .map(ResponseEntity::ok);
//    }

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
