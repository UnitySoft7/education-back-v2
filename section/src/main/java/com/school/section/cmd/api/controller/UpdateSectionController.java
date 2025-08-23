package com.school.section.cmd.api.controller;

import com.school.section.cmd.api.command.SectionUpdatedCommand;
import com.school.section.core.dto.MessageResponse;
import com.school.section.core.model.Section;
import com.school.section.core.payload.SectionPayload;
import com.school.section.core.utils.MessageUtilsConstants;
import com.school.section.query.api.handler.SectionEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(path = "/api/v1/education/section/update-section")
@Tag(name = "Section", description = "Data rest API for update resource")
public class UpdateSectionController {
    private final SectionEventHandler updateEventHandler;
    private final SectionPayload updatePayload;
    /**
     * This method is used to update the update state to available
     * @param command the command containing the update information
     * @return the message for operation
     */
    @Operation(summary = "update")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@RequestBody SectionUpdatedCommand command) {
        return updatePayload.isSectionCodeExist(command.sectionCode ())
                .flatMap(exist -> {
                    if (exist) {
                        Mono<Section> updateMono = updateEventHandler.update (command);
                        return updateMono.flatMap(update -> {
                            if (update != null) {
                                return Mono.just(ResponseEntity.ok()
                                        .body(new MessageResponse(true, MessageUtilsConstants.updated)));
                            }
                            return Mono.just(ResponseEntity.ok()
                                    .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                        });
                    }
                    return Mono.just(ResponseEntity.ok()
                            .body(new MessageResponse(false, MessageUtilsConstants.code_not_found)));
                });
    }
}
