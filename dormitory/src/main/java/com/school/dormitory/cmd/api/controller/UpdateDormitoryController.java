package com.school.dormitory.cmd.api.controller;
import com.school.dormitory.cmd.api.command.DormitoryUpdatedCommand;
import com.school.dormitory.core.dto.MessageResponse;
import com.school.dormitory.core.payload.DormitoryPayload;
import com.school.dormitory.core.utils.MessageUtilsConstants;
import com.school.dormitory.query.api.handler.DormitoryEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/dormitory/update-dormitory")
@Tag(name = "Dormitory", description = "Data rest API for updadte resource")
public class UpdateDormitoryController {
    private final DormitoryEventHandler updadteEventHandler;
    private final DormitoryPayload updadtePayload;
    /**
     * This method is used to update the updadte state to available
     * @param command the command containing the updadte information
     * @return the message for operation
     */
    @Operation(summary = "updadte")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@RequestBody DormitoryUpdatedCommand command) {
        return updadtePayload.isDormitoryCodeExist(command.dormitoryCode ())
                .flatMap(exist -> {
                    if (exist) {
                        Mono<com.school.dormitory.core.model.Dormitory> updadteMono = updadteEventHandler.update (command);
                        return updadteMono.flatMap(updadte -> {
                            if (updadte != null) {
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
