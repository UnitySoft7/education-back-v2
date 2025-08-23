package com.school.dormitory.student.bed.press.cmd.api.controller;
import com.school.dormitory.student.bed.press.cmd.api.command.DormitoryStudentBedPressUpdatedCommand;
import com.school.dormitory.student.bed.press.core.dto.MessageResponse;
import com.school.dormitory.student.bed.press.core.payload.DormitoryStudentBedPressPayload;
import com.school.dormitory.student.bed.press.core.utils.MessageUtilsConstants;
import com.school.dormitory.student.bed.press.query.api.handler.DormitoryStudentBedPressEventHandler;
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
@RequestMapping(path = "/api/v1/education/dormitory-student-bed-press/update-dormitory-student-bed-press")
@Tag(name = "Dormitory-Student-Bed-Press", description = "Data rest API for DormitoryStudentBedPress resource")
public class UpdateDormitoryStudentBedPressController {
    private final DormitoryStudentBedPressEventHandler dormitoryStudentBedPressEventHandler;
    private final DormitoryStudentBedPressPayload dormitoryStudentBedPressPayload;
    @Operation(summary = "Update DormitoryStudentBedPress")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update (@Valid @RequestBody DormitoryStudentBedPressUpdatedCommand command) {
        return dormitoryStudentBedPressPayload.verifyBed(command.bed()).flatMap(bedResponse -> {
            if (bedResponse.success()) {
                return dormitoryStudentBedPressPayload.verifyPress(command.press()).flatMap(pressResponse -> {
                    if (pressResponse.success()) {
//                        return dormitoryStudentBedPressPayload.verifyESCSCode(command.student()).flatMap(studentResponse -> {
//                            if (studentResponse.success()) {
                        return dormitoryStudentBedPressPayload.verifyDormotory(command.dormitory()).flatMap(dormoResponseResponse -> {
                            if (dormoResponseResponse.success()) {
                                return dormitoryStudentBedPressEventHandler.update(command).flatMap(establishmentSectionClassCourse -> {
                                    if (establishmentSectionClassCourse != null) {
                                        return Mono.just(ResponseEntity.ok().body(new MessageResponse(true, MessageUtilsConstants.updated)));
                                    }return Mono.just(ResponseEntity.badRequest().body(new MessageResponse(false, MessageUtilsConstants.operation_failed))); });
                            } else {return Mono.just(ResponseEntity.badRequest().body(new MessageResponse(false, MessageUtilsConstants.code_dormitory_not_found)));}});
//                        } else {return Mono.just(ResponseEntity.badRequest().body(new MessageResponse(false, MessageUtilsConstants.code_student_not_found)));}});
                    } else { return Mono.just(ResponseEntity.badRequest().body(new MessageResponse(false, MessageUtilsConstants.code_press_not_found)));}});
            } else { return Mono.just(ResponseEntity.badRequest().body(new MessageResponse(false, MessageUtilsConstants.code_bed_not_found)));}});
    }
}