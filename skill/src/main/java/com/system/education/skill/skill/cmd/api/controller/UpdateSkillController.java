package com.system.education.skill.skill.cmd.api.controller;

import com.system.education.skill.skill.cmd.api.command.SkillUpdatedCommand;
import com.system.education.skill.core.dto.MessageResponse;
import com.system.education.skill.skill.core.payload.SkillPayload;
import com.system.education.skill.core.utils.SkillUtilsConstants;
import com.system.education.skill.skill.query.api.handler.SkillEventHandler;
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
@RequestMapping(path = "/api/v1/education/skill/update-skill")
@Tag(name = "Skill", description = "Data rest API for skill resource")
public class UpdateSkillController {
    private final SkillEventHandler skillEventHandler;
    private final SkillPayload skillPayload;

    /**
     * This method is used to update a skill
     * @param command the command containing the skill information
     * @return the message for operation
     */
    @Operation(summary = "Update skill")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update (@Valid @RequestBody SkillUpdatedCommand command) {
        return skillPayload.isSkillCodeExist(command.skillCode())
                .flatMap(isExists -> {
                    if (isExists) {
                        return skillEventHandler.update(command)
                                .flatMap(skill -> {
                                    if (skill != null) {
                                        return Mono.just(ResponseEntity.ok()
                                                .body(new MessageResponse(true, SkillUtilsConstants.done)));
                                    }
                                    return Mono.just(ResponseEntity.badRequest()
                                            .body(new MessageResponse(false, SkillUtilsConstants.operation_failed)));
                                });
                    } else {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, SkillUtilsConstants.code_not_found)));
                    }
                });

    }
}
