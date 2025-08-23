package com.system.education.bulletin.detail.cmd.api.controller;

import com.system.education.bulletin.core.dto.MessageResponse;
import com.system.education.bulletin.core.utils.SkillUtilsConstants;
import com.system.education.bulletin.detail.cmd.api.command.BulletinDetailCreatedCommand;
import com.system.education.bulletin.detail.core.payload.BulletinDetailPayload;
import com.system.education.bulletin.detail.query.api.handler.BulletinDetailEventHandler;
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
@RequestMapping(path = "/api/v1/education/bulletin/create-bulletin-detail")
@Tag(name = "Bulletin-detail", description = "Data rest API for bulletin-detail resource")
public class CreateBulletinDetailController {
    private final BulletinDetailEventHandler bulletinDetailEventHandler;
    private final BulletinDetailPayload bulletinDetailPayload;

    /**
     * This method is used to create a new bulletin-detail
     * @param command the command containing the bulletin-detail information
     * @return the message for operation
     */
    @Operation(summary = "Create bulletin-detail")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody BulletinDetailCreatedCommand command) {
        return bulletinDetailPayload.createBulletinDetailExist(command)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, SkillUtilsConstants.bulletin_detail_exist)));
                    }
                    return bulletinDetailEventHandler.create(command)
                            .flatMap(bulletinDetail -> {
                                if (bulletinDetail != null) {
                                    return Mono.just(ResponseEntity.ok()
                                            .body(new MessageResponse(true, SkillUtilsConstants.done)));
                                }
                                return Mono.just(ResponseEntity.badRequest()
                                        .body(new MessageResponse(false, SkillUtilsConstants.operation_failed)));
                            });
                });

    }
}
