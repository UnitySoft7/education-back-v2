package com.school.minos.tariff.cmd.api.controller;

import com.school.minos.core.dto.MessageResponse;
import com.school.minos.minos.core.model.Minos;
import com.school.minos.core.utils.MessageUtilsConstants;
import com.school.minos.tariff.cmd.api.command.TariffCreatedCommand;
import com.school.minos.tariff.core.model.Tariff;
import com.school.minos.tariff.query.api.handler.TariffEventHandler;
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
@RequestMapping(path = "/api/v1/education/minos/create-tariff")
@Tag(name = "Tariff", description = "Data rest API for tariff resource")
public class CreateTariffController {
    private final TariffEventHandler tariffEventHandler;

    @Operation(summary = "Create tariff")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody TariffCreatedCommand command) {

        return tariffEventHandler.create(command).flatMap(disable -> {
            if (disable != null) {
                return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.created)));
            }
            return Mono.just(ResponseEntity.ok().body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
        });
    }
}
