package com.school.section.cmd.api.controller;
import com.school.section.cmd.api.command.SectionCreatedCommand;
import com.school.section.core.dto.MessageResponse;
import com.school.section.core.model.Section;
import com.school.section.core.payload.SectionPayload;
import com.school.section.core.utils.MessageUtilsConstants;
import com.school.section.query.api.handler.SectionEventHandler;
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
@RequestMapping(path = "/api/v1/education/section/create-section")
@Tag(name = "Section", description = "Data rest API for Section resource")
public class CreateSectionController {
    private final SectionEventHandler sectionEventHandler;
    private  final SectionPayload sectionPayload;

    @Operation(summary = "Create Section")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody SectionCreatedCommand command) {
        return sectionPayload.isSectionNameExist(command.sectionName())
                .flatMap(nameExists -> {
                    if (nameExists) {
                        return Mono.just(ResponseEntity.ok(
                                new MessageResponse(false, MessageUtilsConstants.Name_Exists)
                        ));
                    } else {
                        Mono<Section> courseMono = sectionEventHandler.create(command);
                        return courseMono.flatMap(create -> {
                            if (create != null) {
                                return Mono.just(ResponseEntity.ok()
                                        .body(new MessageResponse(true, MessageUtilsConstants.created)));
                            }
                            return Mono.just(ResponseEntity.ok()
                                    .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                        });
                    }
                })
                ;}
}






