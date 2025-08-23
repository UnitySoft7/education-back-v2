package com.system.education.bulletin.detail.cmd.api.controller;

import com.system.education.bulletin.core.dto.MessageResponse;
import com.system.education.bulletin.core.utils.SkillUtilsConstants;
import com.system.education.bulletin.detail.cmd.api.command.BulletinDetailUpdatedCommand;
import com.system.education.bulletin.detail.query.api.handler.BulletinDetailEventHandler;
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
@RequestMapping(path = "/api/v1/education/bulletin/update-bulletin-detail")
@Tag(name = "Bulletin-detail", description = "Data rest API for bulletin-detail resource")
public class UpdateBulletinDetailController {
    private final BulletinDetailEventHandler bulletinDetailEventHandler;

    /**
     * This method is used to update a bulletin-detail
     * @param command the command containing the bulletin-detail information
     * @return the message for operation
     */
    @Operation(summary = "Update bulletin-detail")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody BulletinDetailUpdatedCommand command) {
        return bulletinDetailEventHandler.update(command)
                .flatMap(bulletinDetail -> {
                    if (bulletinDetail != null) {
                        return Mono.just(ResponseEntity.ok()
                                .body(new MessageResponse(true, SkillUtilsConstants.done)));
                    }
                    return Mono.just(ResponseEntity.badRequest()
                            .body(new MessageResponse(false, SkillUtilsConstants.operation_failed)));
                });
    }
}
