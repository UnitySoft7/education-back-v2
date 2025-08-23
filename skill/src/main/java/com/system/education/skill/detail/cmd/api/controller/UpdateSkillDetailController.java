package com.system.education.skill.detail.cmd.api.controller;

import com.system.education.skill.core.dto.MessageResponse;
import com.system.education.skill.core.utils.SkillUtilsConstants;
import com.system.education.skill.detail.cmd.api.command.SkillDetailUpdatedCommand;
import com.system.education.skill.detail.query.api.handler.SkillDetailEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/skill/update-skill-detail")
@Tag(name = "Skill-detail", description = "Data rest API for skill-detail resource")
public class UpdateSkillDetailController {
    private final SkillDetailEventHandler skillDetailEventHandler;

    /**
     * This method is used to update a skill-detail
     * @param command the command containing the skill-detail information
     * @return the message for operation
     */
    @Operation(summary = "Update skill-detail")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody SkillDetailUpdatedCommand command) {
        return skillDetailEventHandler.update(command)
                .flatMap(skillDetail -> {
                    if (skillDetail != null) {
                        return Mono.just(ResponseEntity.ok()
                                .body(new MessageResponse(true, SkillUtilsConstants.done)));
                    }
                    return Mono.just(ResponseEntity.badRequest()
                            .body(new MessageResponse(false, SkillUtilsConstants.operation_failed)));
                });
    }
}
