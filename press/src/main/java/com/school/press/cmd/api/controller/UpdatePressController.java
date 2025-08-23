package com.school.press.cmd.api.controller;
import com.school.press.cmd.api.command.PressUpdatedCommand;
import com.school.press.core.dto.MessageResponse;
import com.school.press.core.payload.PressPayload;
import com.school.press.core.utils.MessageUtilsConstants;
import com.school.press.query.api.handler.PressEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/press/update-press")
@Tag(name = "Press", description = "Data rest API for updadte resource")
public class UpdatePressController {
    private final PressEventHandler updadteEventHandler;
    private final PressPayload updadtePayload;
    /**
     * This method is used to update the updadte state to available
     * @param command the command containing the updadte information
     * @return the message for operation
     */
    @Operation(summary = "updadte")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@RequestBody PressUpdatedCommand command) {
//        return updadtePayload.isPressCodeExist(command.pressCode ())
//                .flatMap(exist -> {
//                    if (exist) {
                        Mono<com.school.press.core.model.Press> updadteMono = updadteEventHandler.update (command);
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
