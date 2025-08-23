package com.system.education.infirmary.product.cmd.api.controller;

import com.system.education.infirmary.product.cmd.api.command.ProductUpdatedCommand;
import com.system.education.infirmary.core.dto.MessageResponse;
import com.system.education.infirmary.product.core.payload.ProductPayload;
import com.system.education.infirmary.core.utils.CafeteriaUtilsConstants;
import com.system.education.infirmary.product.query.api.handler.ProductEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/infirmary/update-product")
@Tag(name = "Product", description = "Data rest API for product resource")
public class UpdateProductController {
    private final ProductEventHandler productEventHandler;
    private final ProductPayload productPayload;

    /**
     * This method is used to update a product
     * @param command the command containing the product information
     * @return the message for operation
     */
    @Operation(summary = "Update product")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update (@Valid @RequestBody ProductUpdatedCommand command) {
        return productPayload.isProductCodeExist(command.productCode())
                .flatMap(isExists -> {
                    if (isExists) {
                        return productEventHandler.update(command)
                                .flatMap(product -> {
                                    if (product != null) {
                                        return Mono.just(ResponseEntity.ok()
                                                .body(new MessageResponse(true, CafeteriaUtilsConstants.done)));
                                    }
                                    return Mono.just(ResponseEntity.badRequest()
                                            .body(new MessageResponse(false, CafeteriaUtilsConstants.operation_failed)));
                                });
                    } else {
                        return Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, CafeteriaUtilsConstants.code_not_found)));
                    }
                });

    }
}
