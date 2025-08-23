package com.school.dormitory.student.bed.press.query.api.controller;
import com.school.dormitory.student.bed.press.cmd.api.command.query.FindByCodeQuery;
import com.school.dormitory.student.bed.press.query.api.dto.AllLookupDormitoryStudentBedPressResponse;
import com.school.dormitory.student.bed.press.query.api.dto.LookupDormitoryStudentBedPressResponse;
import com.school.dormitory.student.bed.press.query.api.handler.DormitoryStudentBedPressQueryHandler;
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
@RequestMapping(path = "/api/v1/education/dormitory-student-bed-press/lookup-dormitory-student-bed-press/")
@Tag(name = "Dormitory-Student-Bed-Press", description = "Data REST API for class resource")
public class DormitoryStudentBedPressLookupController {
    private final DormitoryStudentBedPressQueryHandler dormitoryStudentBedPressQueryHandler;
    @Operation(summary = "Retrieve data DormitoryStudentBedPress")
    @GetMapping(path = "get-dormitories", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupDormitoryStudentBedPressResponse>> show() {
        return dormitoryStudentBedPressQueryHandler.findDormitoryStudentBedPresss()
                .collectList()
                .map(DormitoryStudentBedPress ->
                        new AllLookupDormitoryStudentBedPressResponse (true, DormitoryStudentBedPress))
                .map(ResponseEntity::ok);
    }


    @Operation(summary = "Retrieve data DormitoryStudentBedPress by dormitoryStudentBedPressCode")
    @PutMapping(path = "get-dormitoryStudentBedPress-by-dormitoryStudentBedPress-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getDormitoryStudentBedPressByDormitoryStudentBedPressCode(@Valid @RequestBody FindByCodeQuery query)  {
        return dormitoryStudentBedPressQueryHandler.findDormitoryStudentBedPressByDormitoryStudentBedPressCode (query.code())
                .map(Classroom ->
                        new LookupDormitoryStudentBedPressResponse (true, Classroom))
                .map(ResponseEntity::ok);
    }



}
