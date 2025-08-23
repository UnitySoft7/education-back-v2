package com.system.education.establishment.query.api.controller;

import com.system.education.establishment.query.api.dto.AllLookupEstablishmentResponse;
import com.system.education.establishment.query.api.dto.AllLookupSemesterResponse;
import com.system.education.establishment.query.api.dto.LookupEstablishmentResponse;
import com.system.education.establishment.query.api.handler.EstablishmentQueryHandler;
import com.system.education.establishment.query.api.query.EstablishmentByCodeQuery;
import com.system.education.establishment.query.api.query.EstablishmentByIdQuery;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/establishment/establishment-lookup/")
@Tag(name = "Establishment", description = "Data REST API for establishment resource")
public class EstablishmentLookupController {
    private final EstablishmentQueryHandler establishmentQueryHandler;

    /**
     * This method is used to retrieve all establishments
     * @return the list of establishments
     */
    @Operation(summary = "Retrieve data establishments")
    @GetMapping(path = "get-establishments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupEstablishmentResponse>> show() {
        return establishmentQueryHandler.getEstablishments()
                .collectList()
                .map(establishments ->
                        new AllLookupEstablishmentResponse(true, establishments))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve an establishment by ID
     * @param query the ID of the establishment
     * @return the establishment with the specified ID
     */
    @Operation(summary = "Retrieve data establishment by ID")
    @PutMapping(path = "get-establishment-by-establishment-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody EstablishmentByIdQuery query) {
        return establishmentQueryHandler.getEstablishmentById(query)
                .map(establishment ->
                        new LookupEstablishmentResponse(true, establishment))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve an establishment by establishment code
     * @param query the code of the establishment
     * @return the establishment with the specified code
     */
    @Operation(summary = "Retrieve data establishment by code")
    @PutMapping(path = "get-establishment-by-establishment-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupEstablishmentResponse>> getByEstablishmentCode(@Valid @RequestBody EstablishmentByCodeQuery query) {
        return establishmentQueryHandler.getEstablishmentByCode(query)
                .map(establishment ->
                        new LookupEstablishmentResponse(true, establishment))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body(new LookupEstablishmentResponse(false, null))));
    }

    @Operation(summary = "Retrieve semesters")
    @GetMapping(path = "get-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupSemesterResponse>> showSemesters() {
        return Mono.just(ResponseEntity.ok(new AllLookupSemesterResponse(true, establishmentQueryHandler.getSemesters())));
    }

    @Operation(summary = "Retrieve data establishment logo by code")
    @PutMapping(path = "get-establishment-logo-by-establishment-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UrlResource>> getProfileByStudentCode(@Valid @RequestBody EstablishmentByCodeQuery query) {
        return establishmentQueryHandler.getLogoByCode(query)
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
}
