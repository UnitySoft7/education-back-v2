package com.school.bed.cmd.api.controller;
import com.school.bed.cmd.api.command.BedCreatedCommand;
import com.school.bed.core.dto.MessageResponse;
import com.school.bed.core.model.Bed;
import com.school.bed.core.payload.BedPayload;
import com.school.bed.core.utils.MessageUtilsConstants;
import com.school.bed.query.api.handler.BedEventHandler;
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
@RequestMapping(path = "/api/v1/education/bed/create-bed")
@Tag(name = "Bed", description = "Data rest API for Bed resource")
public class CreateBedController {
    private final BedEventHandler BedEventHandler;
    private final BedPayload bedPayload;
    @Operation(summary = "Create Bed")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody BedCreatedCommand command) {
                  Mono<Bed> bedMono = BedEventHandler.create(command);
                  return bedMono.flatMap(create -> {
                      if (create != null) {
                          return Mono.just(ResponseEntity.ok()
                                  .body(new MessageResponse(true, MessageUtilsConstants.created)));
                      }
                      return Mono.just(ResponseEntity.ok()
                              .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                  });
              }
}




