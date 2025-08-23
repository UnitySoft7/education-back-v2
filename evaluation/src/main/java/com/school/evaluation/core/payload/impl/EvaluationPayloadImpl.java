package com.school.evaluation.core.payload.impl;
import com.school.evaluation.cmd.api.query.FindByCodeQuery;
import com.school.evaluation.core.common.EvaluationCode;
import com.school.evaluation.core.payload.EvaluationPayload;
import com.school.evaluation.query.api.dto.LookupCourseTeacherResponse;
import com.school.evaluation.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.evaluation.query.api.dto.LookupEstablishmentSectionClassResponse;
import com.school.evaluation.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import com.school.evaluation.query.api.repository.EvaluationRepositories;
import com.school.evaluation.query.api.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class EvaluationPayloadImpl implements EvaluationPayload {
    private final EvaluationRepository   conduiteRepository;
    private final EvaluationRepositories conduiteRepositories;
    @Override
    public
    Mono<String> getCode ( ){
        return conduiteRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("EVN100000000001");
            }
            else {
                Mono<String> code = conduiteRepositories.getLastEvaluation()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return EvaluationCode.generate(code);
            }
        });
    }
    @Override
    public
    Mono<Boolean> isEvaluationCodeExist ( String EvaluationCode ) {
        return conduiteRepository.existsEvaluationByCode (EvaluationCode);
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
    public Mono<LookupEstablishmentSectionClassStudentResponse> verifyESCSCode(String code) {
        FindByCodeQuery query = new FindByCodeQuery(code);
        return WebClient.create().put().uri("http://127.0.0.1:9909/api/v1/education/establishment-section-class-student/establishment-section-class-student-lookup/get-establishment-section-class-student-by-establishment-section-class-student-code").bodyValue(query).retrieve().bodyToMono(LookupEstablishmentSectionClassStudentResponse.class);
    }
//http://127.0.0.1:9909/api/v1/education/establishment-section-class-student/establishment-section-class-student-lookup/get-establishment-section-class-student-by-establishment-section-class-student-code
}
