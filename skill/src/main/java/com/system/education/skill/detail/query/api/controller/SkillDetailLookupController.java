package com.system.education.skill.detail.query.api.controller;

import com.system.education.skill.detail.query.api.dto.AllLookupSkillDetailResponse;
import com.system.education.skill.detail.query.api.dto.LookupRatingResponse;
import com.system.education.skill.detail.query.api.dto.LookupSkillDetailResponse;
import com.system.education.skill.detail.query.api.handler.SkillDetailQueryHandler;
import com.system.education.skill.detail.query.api.query.SkillDetailByCodeAndSemesterQuery;
import com.system.education.skill.skill.query.api.query.SkillByCodeQuery;
import com.system.education.skill.skill.query.api.query.SkillByIdQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/skill/skill-detail-lookup/")
@Tag(name = "Skill-detail", description = "Data REST API for skill-detail resource")
public class SkillDetailLookupController {
    private final SkillDetailQueryHandler skillDetailQueryHandler;

    /**
     * This method is used to retrieve all skill-details
     * @return the list of skill-details
     */
    @Operation(summary = "Retrieve data skill-details")
    @GetMapping(path = "get-skill-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupSkillDetailResponse>> show() {
        return skillDetailQueryHandler.getSkillDetails()
                .collectList()
                .map(typeResponses ->
                        new AllLookupSkillDetailResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a skill-detail by ID
     * @param query the ID of the skill-detail
     * @return the skill-detail with the specified ID
     */
    @Operation(summary = "Retrieve data skill-detail by ID")
    @PutMapping(path = "get-skill-detail-by-skill-detail-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody SkillByIdQuery query) {
        return skillDetailQueryHandler.getSkillDetailById(query)
                .map(typeResponse ->
                        new LookupSkillDetailResponse(true, typeResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a skill-detail by establishment code
     * @param query the code of the establishment
     * @return the skill-detail with the specified code
     */
    @Operation(summary = "Retrieve data skill-detail by establishment")
    @PutMapping(path = "get-skill-detail-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody SkillByCodeQuery query) {
        return skillDetailQueryHandler.getSkillDetailByEstablishment(query)
                .collectList()
                .map(typeResponses ->
                        new AllLookupSkillDetailResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data skill-detail by establishment-and-semester")
    @PutMapping(path = "get-skill-detail-by-establishment-and-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody SkillDetailByCodeAndSemesterQuery query) {
        return skillDetailQueryHandler.getSkillDetailByEstablishmentAndSemester(query)
                .collectList()
                .map(skillDetailResponses ->
                        new AllLookupSkillDetailResponse(true, skillDetailResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data skill-detail by student-and-semester")
    @PutMapping(path = "get-skill-detail-by-student-and-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByStudent(@Valid @RequestBody SkillDetailByCodeAndSemesterQuery query) {
        return skillDetailQueryHandler.getSkillDetailByStudentAndSemester(query)
                .collectList()
                .map(skillDetailResponses ->
                        new AllLookupSkillDetailResponse(true, skillDetailResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data skill-detail by skill-and-semester")
    @PutMapping(path = "get-skill-detail-by-skill-and-semester", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getBySkillDetail(@Valid @RequestBody SkillDetailByCodeAndSemesterQuery query) {
        return skillDetailQueryHandler.getSkillDetailBySkillAndSemester(query)
                .collectList()
                .map(skillDetailResponses ->
                        new AllLookupSkillDetailResponse(true, skillDetailResponses))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data skill-detail by skill-and-semester")
    @GetMapping(path = "get-rating-skill", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getRating() {
        return Mono.just(ResponseEntity.ok(new LookupRatingResponse(true, skillDetailQueryHandler.getRating())));

    }
}
