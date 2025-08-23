package com.system.education.bulletin.bulletin.cmd.api.controller;

import com.system.education.bulletin.bulletin.cmd.api.command.BulletinCreatedCommand;
import com.system.education.bulletin.core.dto.MessageResponse;
import com.system.education.bulletin.core.utils.SkillUtilsConstants;
import com.system.education.bulletin.bulletin.query.api.handler.BulletinEventHandler;
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
@RequestMapping(path = "/api/v1/education/bulletin/create-bulletin")
@Tag(name = "Bulletin", description = "Data rest API for bulletin resource")
public class CreateBulletinController {
    private final BulletinEventHandler bulletinEventHandler;

    /**
     * This method is used to create a new bulletin
     * @param command the command containing the bulletin information
     * @return the message for operation
     */
    @Operation(summary = "Create bulletin")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody BulletinCreatedCommand command) {
        return bulletinEventHandler.create(command)
                .flatMap(bulletin -> {
                    if (bulletin != null) {
                        return Mono.just(ResponseEntity.ok()
                                .body(new MessageResponse(true, SkillUtilsConstants.done)));
                    }
                    return Mono.just(ResponseEntity.badRequest()
                            .body(new MessageResponse(false, SkillUtilsConstants.operation_failed)));
                });
    }
}
