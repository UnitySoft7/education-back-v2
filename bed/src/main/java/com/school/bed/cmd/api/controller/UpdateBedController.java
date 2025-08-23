package com.school.bed.cmd.api.controller;
import com.school.bed.cmd.api.command.BedUpdatedCommand;
import com.school.bed.core.dto.MessageResponse;
import com.school.bed.core.model.Bed;
import com.school.bed.core.payload.BedPayload;
import com.school.bed.core.utils.MessageUtilsConstants;
import com.school.bed.query.api.handler.BedEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/bed/update-bed")
@Tag(name = "Bed", description = "Data rest API for updadte resource")
public class UpdateBedController {
    private final BedEventHandler updadteEventHandler;
    private final BedPayload updadtePayload;

    @Operation(summary = "updadte")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update (@RequestBody BedUpdatedCommand command) {
//        return updadtePayload.isBedCodeExist(command.bedCode ())
//                .flatMap(exist -> {
//                    if (exist) {
                        Mono<Bed> updadteMono = updadteEventHandler.update (command);
                        return updadteMono.flatMap(updadte -> {
                            if (updadte != null) {
                                return Mono.just(ResponseEntity.ok()
                                        .body(new MessageResponse(true, MessageUtilsConstants.updated)));
                            }
                            return Mono.just(ResponseEntity.ok()
                                    .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                        });
//                    }
//                    return Mono.just(ResponseEntity.ok()
//                            .body(new MessageResponse(false, MessageUtilsConstants.code_not_found)));
//                });
    }
}
