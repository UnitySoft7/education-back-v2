package com.system.education.establishment.cmd.api.controller;

import com.system.education.establishment.cmd.api.command.EstablishmentUpdatedCommand;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/establishment/update-establishment")
@Tag(name = "Establishment", description = "Data rest API for establishment resource")
public class UpdateEstablishmentController {
    private final EstablishmentEventHandler establishmentEventHandler;
    private final EstablishmentPayload establishmentPayload;

    /**
     * This method is used to update the establishment state to update
     * @param command the command containing the establishment information
     * @return the message for operation
     */
    @Operation(summary = "Hired establishment")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> update (@Valid @RequestBody EstablishmentUpdatedCommand command) {
        return establishmentPayload.isEstablishmentCodeExist(command.establishmentCode())
                .flatMap(exist -> {
                    if (exist) {
                        return establishmentEventHandler.update(command).flatMap(establishment -> {
                            if (establishment != null) {
                                return Mono.just(ResponseEntity.ok()
                                        .body(new MessageResponse(true,EstablishmentUtilsConstants.done)));
                            }
                            return Mono.just(ResponseEntity.ok()
                                    .body(new MessageResponse(false, EstablishmentUtilsConstants.operation_failed)));
                        });
                    }
                    return Mono.just(ResponseEntity.ok()
                            .body(new MessageResponse(false, EstablishmentUtilsConstants.code_not_found)));
                });

    }
}
