package com.school.section.query.api.controller;

import com.school.section.query.api.dto.AllLookupSectionResponse;
import com.school.section.query.api.dto.LookupSectionResponse;
import com.school.section.query.api.handler.SectionQueryHandler;
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
@RequestMapping(path = "/api/v1/education/section/lookup-section/")
@Tag(name = "Section", description = "Data REST API for class resource")
public class SectionLookupController {
    private final SectionQueryHandler sectionQueryHandler;

    @Operation(summary = "Retrieve data Section")
    @GetMapping(path = "get-sections", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupSectionResponse>> show() {
        return sectionQueryHandler.findSections()
                .collectList()
                .map(Section ->
                        new AllLookupSectionResponse(true, Section))
                .map(ResponseEntity::ok);
    }


    @Operation(summary = "Retrieve data section by sectionCode")
    @GetMapping(path = "get-section-by-section-code/{sectionCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupSectionResponse>> getSectionBySectionCode(@PathVariable("sectionCode") String sectionCode ) {
        return sectionQueryHandler.findSectionBySectionCode (sectionCode)
                .map(section ->
                        new LookupSectionResponse (true, section))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupSectionResponse(false, null))));
    }

    @Operation(summary = "Retrieve data section by establishment")
    @GetMapping(path = "get-section-by-establishment/{establishmentCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupSectionResponse>> getSectionByEstablishmentCode(@PathVariable("establishmentCode") String establishmentCode ) {
        return sectionQueryHandler.findSectionByEstablishmentCode (establishmentCode)
                .collectList()
                .map(sectionResponses ->
                        new AllLookupSectionResponse (true, sectionResponses))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new AllLookupSectionResponse(false, List.of()))));
    }
}
