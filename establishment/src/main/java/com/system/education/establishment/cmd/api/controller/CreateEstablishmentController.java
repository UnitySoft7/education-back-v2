package com.system.education.establishment.cmd.api.controller;

import com.system.education.establishment.cmd.api.command.EstablishmentCreatedCommand;
import com.system.education.establishment.core.dto.MessageResponse;
import com.system.education.establishment.core.payload.EstablishmentPayload;
import com.system.education.establishment.core.utils.EstablishmentUtilsConstants;
import com.system.education.establishment.query.api.handler.EstablishmentEventHandler;
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
@RequestMapping(path = "/api/v1/education/establishment/create-establishment")
@Tag(name = "Establishment", description = "Data rest API for establishment resource")
public class CreateEstablishmentController {
    private final EstablishmentEventHandler establishmentEventHandler;
    private final EstablishmentPayload establishmentPayload;

    /**
     * This method is used to create a new establishment
     * @param command the command containing the establishment information
     * @return the message for operation
     */
    @Operation(summary = "Create establishment")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public Mono<ResponseEntity<MessageResponse>> create (@Valid EstablishmentCreatedCommand command) {
        return establishmentPayload.isEstablishmentNameExist(command.establishmentName())
                .flatMap(exist -> {
                    if (exist) {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, EstablishmentUtilsConstants.name_exist)));
                    }
                    return establishmentEventHandler.create(command)
                            .flatMap(establishment -> {
                                if (establishment != null) {
                                    return Mono.just(ResponseEntity.ok()
                                            .body(new MessageResponse(true,EstablishmentUtilsConstants.created)));
                                }
                                return Mono.just(ResponseEntity.badRequest()
                                        .body(new MessageResponse(false, EstablishmentUtilsConstants.operation_failed)));
                            });
                });

    }
}
