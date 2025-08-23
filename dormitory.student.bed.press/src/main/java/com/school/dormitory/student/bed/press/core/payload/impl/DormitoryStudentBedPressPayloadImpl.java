package com.school.dormitory.student.bed.press.core.payload.impl;
import com.school.dormitory.student.bed.press.cmd.api.command.query.FindByCodeQuery;
import com.school.dormitory.student.bed.press.core.common.DormitoryStudentBedPressCode;
import com.school.dormitory.student.bed.press.core.payload.DormitoryStudentBedPressPayload;
import com.school.dormitory.student.bed.press.query.api.dto.LookupBedResponse;
import com.school.dormitory.student.bed.press.query.api.dto.LookupDormitoryResponse;
import com.school.dormitory.student.bed.press.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import com.school.dormitory.student.bed.press.query.api.dto.LookupPressResponse;
import com.school.dormitory.student.bed.press.query.api.repository.DormitoryStudentBedPressRepositories;
import com.school.dormitory.student.bed.press.query.api.repository.DormitoryStudentBedPressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class DormitoryStudentBedPressPayloadImpl implements DormitoryStudentBedPressPayload {
    private final DormitoryStudentBedPressRepository   dormitoryStudentBedPressRepository;
    private final DormitoryStudentBedPressRepositories dormitoryStudentBedPressRepositories;
    @Override
    public
    Mono<String> getCode(){
        return dormitoryStudentBedPressRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("DSBP10000001"); }
            else {  Mono<String> code = dormitoryStudentBedPressRepositories.getLastDormitoryStudentBedPress()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return DormitoryStudentBedPressCode.generate(code);
            }
        });
    }
    @Override
    public Mono<LookupDormitoryResponse> verifyDormotory(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9839/api/v1/education/dormitory/lookup-dormitory/get-dormitory-by-dormitory-code").bodyValue(query).retrieve().bodyToMono(LookupDormitoryResponse.class);
    }
    @Override
    public Mono<LookupBedResponse> verifyBed(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9840/api/v1/education/bed/lookup-bed/get-bed-by-bed-code").bodyValue(query).retrieve().bodyToMono(LookupBedResponse.class);
    }
    @Override
    public Mono<LookupPressResponse> verifyPress(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9837/api/v1/education/press/lookup-press/get-press-by-press-code").bodyValue(query).retrieve().bodyToMono(LookupPressResponse.class);
    }
    @Override
    public Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9909/api/v1/education/establishment-section-class-student/establishment-section-class-student-lookup/get-establishment-section-class-student-by-establishment-section-class-student-code").bodyValue(query).retrieve().bodyToMono(LookupEstablishmentSectionClassStudentResponse.class);
    }
}
