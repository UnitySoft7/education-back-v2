package com.system.education.skill.skill.cmd.api.controller;

import com.system.education.skill.skill.cmd.api.command.SkillCreatedCommand;
import com.system.education.skill.core.dto.MessageResponse;
import com.system.education.skill.core.utils.SkillUtilsConstants;
import com.system.education.skill.skill.query.api.handler.SkillEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/skill/create-skill")
@Tag(name = "Skill", description = "Data rest API for skill resource")
public class CreateSkillController {
    private final SkillEventHandler skillEventHandler;

    /**
     * This method is used to create a new skill
     * @param command the command containing the skill information
     * @return the message for operation
     */
    @Operation(summary = "Create skill")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody SkillCreatedCommand command) {
        return skillEventHandler.create(command)
                .flatMap(permissionType -> {
                    if (permissionType != null) {
                        return Mono.just(ResponseEntity.ok()
                                .body(new MessageResponse(true, SkillUtilsConstants.done)));
                    }
                    return Mono.just(ResponseEntity.badRequest()
                            .body(new MessageResponse(false, SkillUtilsConstants.operation_failed)));
                });
    }
}
