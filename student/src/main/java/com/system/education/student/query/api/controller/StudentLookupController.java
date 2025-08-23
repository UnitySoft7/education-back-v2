package com.system.education.student.query.api.controller;

import com.system.education.student.query.api.dto.AllLookupGenderResponse;
import com.system.education.student.query.api.dto.AllLookupStudentResponse;
import com.system.education.student.query.api.dto.LookupStudentClassResponse;
import com.system.education.student.query.api.dto.LookupStudentResponse;
import com.system.education.student.query.api.handler.StudentQueryHandler;
import com.system.education.student.query.api.query.StudentByCodeQuery;
import com.system.education.student.query.api.query.StudentByIdQuery;
import com.system.education.student.query.api.response.GenderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/student/student-lookup/")
@Tag(name = "Student", description = "Data REST API for student resource")
public class StudentLookupController {
    private final StudentQueryHandler studentQueryHandler;

    /**
     * This method is used to retrieve all students
     * @return the list of students
     */
    @Operation(summary = "Retrieve data students")
    @GetMapping(path = "get-students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupStudentResponse>> show() {
        return studentQueryHandler.getStudents()
                .collectList()
                .map(students ->
                        new AllLookupStudentResponse(true, students))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a student by ID
     * @param query the ID of the student
     * @return the student with the specified ID
     */
    @Operation(summary = "Retrieve data student by ID")
    @PutMapping(path = "get-student-by-student-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody StudentByIdQuery query) {
        return studentQueryHandler.getStudentById(query)
                .map(student ->
                        new LookupStudentResponse(true, student))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a student by student code
     * @param query the code of the student
     * @return the student with the specified code
     */
    @Operation(summary = "Retrieve data student by code")
    @PutMapping(path = "get-student-by-student-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupStudentResponse>> getByStudentCode(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getStudentByCode(query)
                .map(student ->
                        new LookupStudentResponse(true, student))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupStudentResponse(false, null))));
    }

    /**
     * This method is used to retrieve a student by student code
     * @param query the code of the student
     * @return the student with the specified code
     */
    @Operation(summary = "Retrieve data student by parent")
    @PutMapping(path = "get-student-by-parent", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByParent(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getStudentByParent(query)
                .collectList()
                .map(students ->
                        new AllLookupStudentResponse(true, students))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a student by class code
     * @param query the code of the class
     * @return the student with the specified code
     */
    @Operation(summary = "Retrieve data student by class")
    @PutMapping(path = "get-student-by-class", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByClass(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getStudentByClass(query)
                .collectList()
                .map(students ->
                        new AllLookupStudentResponse(true, students))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a student by section code
     * @param query the code of the section
     * @return the student with the specified code
     */
    @Operation(summary = "Retrieve data student by section")
    @PutMapping(path = "get-student-by-section", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getBySection(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getStudentBySection(query)
                .collectList()
                .map(students ->
                        new AllLookupStudentResponse(true, students))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a student by establishment code
     * @param query the code of the establishment
     * @return the student with the specified code
     */
    @Operation(summary = "Retrieve data student by establishment")
    @PutMapping(path = "get-student-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getStudentByEstablishment(query)
                .collectList()
                .map(students ->
                        new AllLookupStudentResponse(true, students))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data student profile by code")
    @PutMapping(path = "get-student-profile-by-student-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UrlResource>> getProfileByStudentCode(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getStudentProfileByCode(query)
                .map(resource -> {
                    try {
                        Path filePath = Path.of(resource.getURI());
                        String fileName = filePath.getFileName().toString();
                        String contentType = Files.probeContentType(filePath);

                        if (contentType == null) {
                            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
                        }

                        return ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                                .contentType(MediaType.parseMediaType(contentType))
                                .body(resource);
                    } catch (IOException e) {
                        throw new RuntimeException("Impossible de déterminer le type de fichier", e);
                    }
                });
    }

    @Operation(summary = "Retrieve data student extract by code")
    @PutMapping(path = "get-student-extract-by-student-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UrlResource>> getExtractByStudentCode(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getStudentExtractByCode(query)
                .map(resource -> {
                    try {
                        Path filePath = Path.of(resource.getURI());
                        String fileName = filePath.getFileName().toString();
                        String contentType = Files.probeContentType(filePath);

                        if (contentType == null) {
                            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
                        }

                        return ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                                .contentType(MediaType.parseMediaType(contentType))
                                .body(resource);
                    } catch (IOException e) {
                        throw new RuntimeException("Impossible de déterminer le type de fichier", e);
                    }
                });
    }

    @Operation(summary = "Retrieve data student bulletin by code")
    @PutMapping(path = "get-student-bulletin-by-student-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UrlResource>> getBulletinByStudentCode(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getStudentBulletinByCode(query)
                .map(resource -> {
                    try {
                        Path filePath = Path.of(resource.getURI());
                        String fileName = filePath.getFileName().toString();
                        String contentType = Files.probeContentType(filePath);

                        if (contentType == null) {
                            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
                        }

                        return ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                                .contentType(MediaType.parseMediaType(contentType))
                                .body(resource);
                    } catch (IOException e) {
                        throw new RuntimeException("Impossible de déterminer le type de fichier", e);
                    }
                });
    }

    /**
     * This method is used to retrieve genders for students
     */
    @Operation(summary = "Retrieve data teachers")
    @GetMapping(path = "get-genders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupGenderResponse>> genders() {
        List<GenderResponse> genders = new ArrayList<>();
        genders.add(new GenderResponse("MAN"));
        genders.add(new GenderResponse("WOMEN"));
        return Mono.just(ResponseEntity.ok()
                .body(new AllLookupGenderResponse(true, genders)));
    }

    @Operation(summary = "Retrieve data presence-student by class")
    @PutMapping(path = "get-presence-student-by-class", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getPresenceStudentByClass(@Valid @RequestBody StudentByCodeQuery query) {
        return studentQueryHandler.getPresenceStudentByClass(query)
                .map(students ->
                        new LookupStudentClassResponse(true, students))
                .map(ResponseEntity::ok);
    }
}
