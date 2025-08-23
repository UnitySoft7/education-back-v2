package com.school.press.cmd.api.controller;
import com.school.press.cmd.api.command.PressCreatedCommand;
import com.school.press.core.dto.MessageResponse;
import com.school.press.core.model.Press;
import com.school.press.core.payload.PressPayload;
import com.school.press.core.utils.MessageUtilsConstants;
import com.school.press.query.api.handler.PressEventHandler;
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
@RequestMapping(path = "/api/v1/education/press/create-press")
@Tag(name = "Press", description = "Data rest API for Press resource")
public class CreatePressController {
    private final PressEventHandler PressEventHandler;
    private final PressPayload pressPayload;
    @Operation(summary = "Create Press")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody PressCreatedCommand command) {
                  Mono<Press> pressMono = PressEventHandler.create(command);
                  return pressMono.flatMap(create -> {
                      if (create != null) {
                          return Mono.just(ResponseEntity.ok()
                                  .body(new MessageResponse(true, MessageUtilsConstants.created)));
                      }
                      return Mono.just(ResponseEntity.ok()
                              .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                  });
              }
}




