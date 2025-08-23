package com.school.exam.core.payload.impl;
import com.school.exam.cmd.api.query.FindByCodeQuery;
import com.school.exam.core.common.ExamCode;
import com.school.exam.core.payload.ExamPayload;
import com.school.exam.query.api.dto.LookupCourseTeacherResponse;
import com.school.exam.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.exam.query.api.dto.LookupEstablishmentSectionClassResponse;
import com.school.exam.query.api.repository.ExamRepositories;
import com.school.exam.query.api.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class ExamPayloadImpl implements ExamPayload {
    private final ExamRepository   conduiteRepository;
    private final ExamRepositories conduiteRepositories;
    @Override
    public
    Mono<String> getCode ( ){
        return conduiteRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("EX100000000001");
            }
            else {
                Mono<String> code = conduiteRepositories.getLastExam()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return ExamCode.generate(code);
            }
        });
    }
    @Override
    public
    Mono<Boolean> isExamCodeExist ( String ExamCode ) {
        return conduiteRepository.existsExamByCode (ExamCode);
    }


    @Override
    public Mono<LookupEstablishmentSectionClassCourseResponse> verifyESCCCode(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9908/api/v1/education/establishment-section-class-course/establishment-section-class-course-lookup/get-establishment-section-class-course-by-establishment-section-class-course-code").bodyValue(query).retrieve().bodyToMono(LookupEstablishmentSectionClassCourseResponse.class);
    }

    @Override
    public Mono<LookupCourseTeacherResponse> verifyESCCTCode(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9911/api/v1/education/establishment-section-class-course-teacher/establishment-section-class-course-teacher-lookup/get-establishment-section-class-course-teacher-by-establishment-section-class-course-teacher-code").bodyValue(query).retrieve().bodyToMono(LookupCourseTeacherResponse.class);
    }

    @Override
    public Mono<LookupEstablishmentSectionClassResponse> verifyESCCode(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9907/api/v1/education/establishment-section-class/establishment-section-class-lookup/get-establishment-section-class-by-establishment-section-class-code").bodyValue(query).retrieve().bodyToMono(LookupEstablishmentSectionClassResponse.class);
    }

    @Override
    public Mono<Boolean> varifyNote(String note) {
        if (Double.parseDouble(note) > 0 && Double.parseDouble(note) <= 999) {
            return Mono.just(true);
        } else {
            return Mono.just(false);
        }
    }

}