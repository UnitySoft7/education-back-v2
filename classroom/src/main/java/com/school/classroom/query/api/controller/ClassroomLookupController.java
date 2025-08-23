package com.school.classroom.query.api.controller;

import com.school.classroom.query.api.dto.AllLookupClassroomResponse;
import com.school.classroom.query.api.dto.LookupClassroomResponse;
import com.school.classroom.query.api.handler.ClassroomQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/api/v1/education/classroom/lookup-classroom/")
@Tag(name = "Classroom", description = "Data REST API for class resource")
public class ClassroomLookupController {
    private final ClassroomQueryHandler ClassroomQueryHandler;

    @Operation(summary = "Retrieve data Classroom")
    @GetMapping(path = "get-classrooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupClassroomResponse>> show() {
        return ClassroomQueryHandler.findClassrooms().collectList().map(Classroom ->
                new AllLookupClassroomResponse(true, Classroom)).map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data Classroom by classroomCode")
    @GetMapping(path = "get-classroom-by-classroom-code/{classroomCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupClassroomResponse>> getClassroomByClassroomCode(@PathVariable("classroomCode") String classroomCode) {
        return ClassroomQueryHandler.findClassroomByClassroomCode(classroomCode)
                .map(classroom ->
                        ResponseEntity.ok(new LookupClassroomResponse(true, classroom)))
                .switchIfEmpty(Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new LookupClassroomResponse(false, null))
                ));
    }

    @Operation(summary = "Retrieve data Classroom by establishmentCode")
    @GetMapping(path = "get-classroom-by-establishment-code/{establishmentCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupClassroomResponse>> getClassroomByEstablishmentCode(@PathVariable("establishmentCode") String establishmentCode) {
        return ClassroomQueryHandler.findByEstablishmentCode(establishmentCode)
                .collectList()
                .map(classroom ->
                        ResponseEntity.ok(new AllLookupClassroomResponse(true, classroom)))
                .switchIfEmpty(Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new AllLookupClassroomResponse(false, List.of()))
                ));
    }


}
