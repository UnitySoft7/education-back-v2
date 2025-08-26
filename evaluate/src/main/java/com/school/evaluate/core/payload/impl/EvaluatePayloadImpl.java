package com.school.evaluate.core.payload.impl;

import com.school.evaluate.cmd.api.query.FindByCodeQuery;
import com.school.evaluate.core.common.EvaluateCode;
import com.school.evaluate.core.payload.EvaluatePayload;
import com.school.evaluate.query.api.dto.LookupCourseTeacherResponse;
import com.school.evaluate.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.evaluate.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import com.school.evaluate.query.api.repository.EvaluateRepositories;
import com.school.evaluate.query.api.repository.EvaluateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EvaluatePayloadImpl implements EvaluatePayload {
    private final EvaluateRepository conduiteRepository;
    private final EvaluateRepositories conduiteRepositories;

    @Override
    public Mono<String> getCode() {
        return conduiteRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("EV100000000001");
            } else {
                Mono<String> code = conduiteRepositories.getLastEvaluate().flatMap(value -> Mono.just(value.getCode()));
                return EvaluateCode.generate(code);
            }
        });
    }

    @Override
    public Mono<Boolean> isEvaluateCodeExist(String EvaluateCode) {
        return conduiteRepository.existsEvaluateByCode(EvaluateCode);
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
    public Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9909/api/v1/education/establishment-section-class-student/establishment-section-class-student-lookup/get-establishment-section-class-student-by-establishment-section-class-student-code").bodyValue(query).retrieve().bodyToMono(LookupEstablishmentSectionClassStudentResponse.class);
    }

    @Override
    public Mono<Boolean> verifyNote(String note, String noteMax) {
        try {
            double noteValue = Double.parseDouble(note);
            double noteMaxValue = Double.parseDouble(noteMax);
            return Mono.just(noteValue >= 0 && noteValue <= noteMaxValue);
        } catch (NumberFormatException e) {
            return Mono.just(false);
        }
    }

    @Override
    public Mono<Boolean> verifyEvaluate(String evaluationCode, String studentCode) {
        return conduiteRepository.existsByEvaluationCodeAndStudentCode(evaluationCode, studentCode);
    }
}
