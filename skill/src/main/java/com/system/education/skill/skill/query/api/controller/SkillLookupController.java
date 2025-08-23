package com.system.education.skill.skill.query.api.controller;

import com.system.education.skill.skill.query.api.dto.AllLookupSkillResponse;
import com.system.education.skill.skill.query.api.dto.LookupSkillResponse;
import com.system.education.skill.skill.query.api.handler.SkillQueryHandler;
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
@RequestMapping(path = "/api/v1/education/skill/skill-lookup/")
@Tag(name = "Skill", description = "Data REST API for skill resource")
public class SkillLookupController {
    private final SkillQueryHandler skillQueryHandler;

    /**
     * This method is used to retrieve all skills
     * @return the list of skills
     */
    @Operation(summary = "Retrieve data skills")
    @GetMapping(path = "get-skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupSkillResponse>> show() {
        return skillQueryHandler.getSkills()
                .collectList()
                .map(typeResponses ->
                        new AllLookupSkillResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a skill by ID
     * @param query the ID of the skill
     * @return the skill with the specified ID
     */
    @Operation(summary = "Retrieve data skill by ID")
    @PutMapping(path = "get-skill-by-skill-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody SkillByIdQuery query) {
        return skillQueryHandler.getSkillById(query)
                .map(typeResponse ->
                        new LookupSkillResponse(true, typeResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a skill by skill code
     * @param query the code of the skill
     * @return the skill with the specified code
     */
    @Operation(summary = "Retrieve data skill by code")
    @PutMapping(path = "get-skill-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupSkillResponse>> getByCode(@Valid @RequestBody SkillByCodeQuery query) {
        return skillQueryHandler.getSkillByCode(query)
                .map(typeResponse ->
                        new LookupSkillResponse(true, typeResponse))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupSkillResponse(false, null))));
    }

    /**
     * This method is used to retrieve a skill by establishment code
     * @param query the code of the establishment
     * @return the skill with the specified code
     */
    @Operation(summary = "Retrieve data skill by establishment")
    @PutMapping(path = "get-skill-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody SkillByCodeQuery query) {
        return skillQueryHandler.getSkillByEstablishment(query)
                .collectList()
                .map(typeResponses ->
                        new AllLookupSkillResponse(true, typeResponses))
                .map(ResponseEntity::ok);
    }
}
