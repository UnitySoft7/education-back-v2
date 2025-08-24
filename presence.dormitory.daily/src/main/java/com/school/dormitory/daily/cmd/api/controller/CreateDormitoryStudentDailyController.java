package com.school.dormitory.daily.cmd.api.controller;

import com.school.dormitory.daily.cmd.api.command.DormitoryStudentDailyCreatedCommand;
import com.school.dormitory.daily.core.dto.MessageResponse;
import com.school.dormitory.daily.core.model.DormitoryStudentDaily;
import com.school.dormitory.daily.core.payload.DormitoryStudentDailyPayload;
import com.school.dormitory.daily.core.utils.MessageUtilsConstants;
import com.school.dormitory.daily.query.api.handler.DormitoryStudentDailyEventHandler;
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
@RequestMapping(path = "/api/v1/education/dormitory-student-daily/create-dormitory-student-daily")
@Tag(name = "DormitoryStudentDaily", description = "Data rest API for DormitoryStudentDaily resource")
public class CreateDormitoryStudentDailyController {
    private final DormitoryStudentDailyEventHandler DormitoryStudentDailyEventHandler;
    private final DormitoryStudentDailyPayload dormitoryStudentDailyPayload;

    @Operation(summary = "Create DormitoryStudentDaily")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody DormitoryStudentDailyCreatedCommand command) {
        Mono<DormitoryStudentDaily> dormitoryStudentDailyMono = DormitoryStudentDailyEventHandler.create(command);
        return dormitoryStudentDailyMono.flatMap(create -> {
            if (create != null) {
                return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.created)));
            }
            return Mono.just(ResponseEntity.ok().body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
        });
    }
}




