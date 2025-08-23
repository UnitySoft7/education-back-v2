package com.system.education.cafeteria.product.cmd.api.controller;

import com.system.education.cafeteria.product.cmd.api.command.ProductCreatedCommand;
import com.system.education.cafeteria.core.dto.MessageResponse;
import com.system.education.cafeteria.core.utils.CafeteriaUtilsConstants;
import com.system.education.cafeteria.product.query.api.handler.ProductEventHandler;
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
@RequestMapping(path = "/api/v1/education/cafeteria/create-product")
@Tag(name = "Product", description = "Data rest API for product resource")
public class CreateProductController {
    private final ProductEventHandler productEventHandler;

    /**
     * This method is used to create a new product
     * @param command the command containing the product information
     * @return the message for operation
     */
    @Operation(summary = "Create product")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody ProductCreatedCommand command) {
        return productEventHandler.create(command)
                .flatMap(product -> {
                    if (product != null) {
                        return Mono.just(ResponseEntity.ok()
                                .body(new MessageResponse(true, CafeteriaUtilsConstants.done)));
                    }
                    return Mono.just(ResponseEntity.badRequest()
                            .body(new MessageResponse(false, CafeteriaUtilsConstants.operation_failed)));
                });
    }
}
