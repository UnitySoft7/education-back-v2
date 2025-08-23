package com.school.dormitory.cmd.api.controller;
import com.school.dormitory.cmd.api.command.DormitoryCreatedCommand;
import com.school.dormitory.core.dto.MessageResponse;
import com.school.dormitory.core.model.Dormitory;
import com.school.dormitory.core.payload.DormitoryPayload;
import com.school.dormitory.core.utils.MessageUtilsConstants;
import com.school.dormitory.query.api.handler.DormitoryEventHandler;
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
@RequestMapping(path = "/api/v1/education/dormitory/create-dormitory")
@Tag(name = "Dormitory", description = "Data rest API for Dormitory resource")
public class CreateDormitoryController {
    private final DormitoryEventHandler DormitoryEventHandler;
    private final DormitoryPayload dormitoryPayload;
    @Operation(summary = "Create Dormitory")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody DormitoryCreatedCommand command) {
        return dormitoryPayload.isDormitoryNameExist(command.dormitoryName())
          .flatMap(nameExists -> {
              if (nameExists) {
                  return Mono.just(ResponseEntity.ok(
                          new MessageResponse(false, MessageUtilsConstants.Name_exist)
                  ));
              } else {
                  Mono<Dormitory> dormitoryMono = DormitoryEventHandler.create(command);
                  return dormitoryMono.flatMap(create -> {
                      if (create != null) {
                          return Mono.just(ResponseEntity.ok()
                                  .body(new MessageResponse(true, MessageUtilsConstants.created)));
                      }
                      return Mono.just(ResponseEntity.ok()
                              .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                  });
              }
          })
    ;}
}




