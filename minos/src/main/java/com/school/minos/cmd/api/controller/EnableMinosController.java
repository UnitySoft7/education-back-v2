package com.school.minos.cmd.api.controller;

import com.school.minos.cmd.api.command.MinosUpdatedCommand;
import com.school.minos.core.dto.MessageResponse;
import com.school.minos.core.model.Minos;
import com.school.minos.core.payload.MinosPayload;
import com.school.minos.core.utils.MessageUtilsConstants;
import com.school.minos.query.api.handler.MinosEventHandler;
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
@RequestMapping(path = "/api/v1/education/minos/update-minos")
@Tag(name = "Minervale", description = "Data rest API for update resource")
public class EnableMinosController {
    private final MinosEventHandler minosEventHandler;
    private final MinosPayload minosPayload;
    /**
     * This method is used to update the update state to available
     * @param command the command containing the update information
     * @return the message for operation
     */
    @Operation(summary = "update")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@RequestBody MinosUpdatedCommand command) {
        return minosPayload.isMinosCodeExist(command.minosCode ())
                .flatMap(exist -> {
                    if (exist) {
                        Mono<Minos> updateMono = minosEventHandler.update (command);
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
