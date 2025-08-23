package com.school.dormitory.student.bed.press.core.payload;


import com.school.dormitory.student.bed.press.query.api.dto.LookupBedResponse;
import com.school.dormitory.student.bed.press.query.api.dto.LookupDormitoryResponse;
import com.school.dormitory.student.bed.press.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import com.school.dormitory.student.bed.press.query.api.dto.LookupPressResponse;
import reactor.core.publisher.Mono;

public
interface DormitoryStudentBedPressPayload {
    Mono<String> getCode();


    Mono<LookupDormitoryResponse> verifyDormotory(String code);

    Mono<LookupBedResponse> verifyBed(String code);

    Mono<LookupPressResponse> verifyPress(String code);

    Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code);
}