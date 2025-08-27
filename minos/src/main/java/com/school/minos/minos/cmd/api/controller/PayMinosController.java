package com.school.minos.minos.cmd.api.controller;

import com.school.minos.minos.cmd.api.command.MinosPayedCommand;
import com.school.minos.core.dto.MessageResponse;
import com.school.minos.transaction.core.model.Transaction;
import com.school.minos.core.utils.MessageUtilsConstants;
import com.school.minos.minos.query.api.handler.MinosEventHandler;
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
@RequestMapping(path = "/api/v1/education/minos/pay-minos")
@Tag(name = "Minervale", description = "Data rest API for minos resource")
public class PayMinosController {
    private final MinosEventHandler minosEventHandler;

    @Operation(summary = "Create minos")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> pay(@Valid @RequestBody MinosPayedCommand command) {

        Mono<Transaction> paid = minosEventHandler.pay(command);
        return paid.flatMap(pay -> {
            if (pay != null) {
                return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.created)));
            }
            return Mono.just(ResponseEntity.ok().body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
        });
    }
}

