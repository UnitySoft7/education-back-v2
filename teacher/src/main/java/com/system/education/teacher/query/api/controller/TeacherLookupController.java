package com.system.education.teacher.query.api.controller;

import com.system.education.teacher.query.api.dto.AllLookupGenderResponse;
import com.system.education.teacher.query.api.dto.AllLookupTeacherResponse;
import com.system.education.teacher.query.api.dto.LookupNameResponse;
import com.system.education.teacher.query.api.dto.LookupTeacherResponse;
import com.system.education.teacher.query.api.handler.TeacherQueryHandler;
import com.system.education.teacher.query.api.query.TeacherByCodeQuery;
import com.system.education.teacher.query.api.query.TeacherByIdQuery;
import com.system.education.teacher.query.api.response.GenderResponse;
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
@RequestMapping(path = "/api/v1/education/teacher/teacher-lookup/")
@Tag(name = "Teacher", description = "Data REST API for teacher resource")
public class TeacherLookupController {
    private final TeacherQueryHandler teacherQueryHandler;

    /**
     * This method is used to retrieve all teachers
     * @return the list of teachers
     */
    @Operation(summary = "Retrieve data teachers")
    @GetMapping(path = "get-teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTeacherResponse>> show() {
        return teacherQueryHandler.getTeachers()
                .collectList()
                .map(teachers ->
                        new AllLookupTeacherResponse(true, teachers))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a teacher by ID
     * @param query the ID of the teacher
     * @return the teacher with the specified ID
     */
    @Operation(summary = "Retrieve data teacher by ID")
    @PutMapping(path = "get-teacher-by-teacher-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody TeacherByIdQuery query) {
        return teacherQueryHandler.getTeacherById(query)
                .map(teacher ->
                        new LookupTeacherResponse(true, teacher))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a teacher by teacher code
     * @param query the code of the teacher
     * @return the teacher with the specified code
     */
    @Operation(summary = "Retrieve data teacher by code")
    @PutMapping(path = "get-teacher-by-teacher-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupTeacherResponse>> getByTeacherCode(@Valid @RequestBody TeacherByCodeQuery query) {
        return teacherQueryHandler.getTeacherByCode(query)
                .map(teacher ->
                        new LookupTeacherResponse(true, teacher))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupTeacherResponse(false, null))));
    }

    @Operation(summary = "Retrieve data teacher by establishment")
    @PutMapping(path = "get-teacher-by-establishment-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTeacherResponse>> getByEstablishmentCode(@Valid @RequestBody TeacherByCodeQuery query) {
        return teacherQueryHandler.getTeacherByEstablishmentCode(query)
                .collectList()
                .map(teacher ->
                        new AllLookupTeacherResponse(true, teacher))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new AllLookupTeacherResponse(false, List.of()))));
    }

    /**
     * This method is used to retrieve all teachers
     * @return the list of teachers
     */
    @Operation(summary = "Retrieve data teachers")
    @GetMapping(path = "get-genders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupGenderResponse>> genders() {
        List<GenderResponse> genders = new ArrayList<>();
        genders.add(new GenderResponse("HOMME"));
        genders.add(new GenderResponse("FEMME"));
        return Mono.just(ResponseEntity.ok()
                .body(new AllLookupGenderResponse(true, genders)));
    }

    @Operation(summary = "Retrieve functions")
    @GetMapping(path = "get-function", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupNameResponse>> showFunction() {
        return Mono.just(ResponseEntity.ok(new LookupNameResponse(
                true, teacherQueryHandler.getFunction())));
    }

    @Operation(summary = "Retrieve data teachers-infirmary")
    @GetMapping(path = "get-teachers-infirmary", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTeacherResponse>> showInfirmary() {
        return teacherQueryHandler.getTeacherByInfirmary()
                .collectList()
                .map(teachers ->
                        new AllLookupTeacherResponse(true, teachers))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data teachers-cafeteria")
    @GetMapping(path = "get-teachers-cafeteria", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTeacherResponse>> showCafeteria() {
        return teacherQueryHandler.getTeacherByCafeteria()
                .collectList()
                .map(teachers ->
                        new AllLookupTeacherResponse(true, teachers))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data teachers-administration")
    @GetMapping(path = "get-teachers-administration", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTeacherResponse>> showAdministration() {
        return teacherQueryHandler.getTeacherByAdministration()
                .collectList()
                .map(teachers ->
                        new AllLookupTeacherResponse(true, teachers))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data teachers-teacher")
    @GetMapping(path = "get-teachers-teacher", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTeacherResponse>> showTeacher() {
        return teacherQueryHandler.getTeacherByTeacher()
                .collectList()
                .map(teachers ->
                        new AllLookupTeacherResponse(true, teachers))
                .map(ResponseEntity::ok);
    }
}
