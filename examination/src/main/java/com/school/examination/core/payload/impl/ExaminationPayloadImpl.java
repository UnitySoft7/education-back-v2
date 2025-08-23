package com.school.examination.core.payload.impl;
import com.school.examination.cmd.api.query.FindByCodeQuery;
import com.school.examination.core.common.ExaminationCode;
import com.school.examination.core.payload.ExaminationPayload;
import com.school.examination.query.api.dto.LookupCourseTeacherResponse;
import com.school.examination.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.school.examination.query.api.dto.LookupEstablishmentSectionClassStudentResponse;
import com.school.examination.query.api.repository.ExaminationRepositories;
import com.school.examination.query.api.repository.ExaminationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class ExaminationPayloadImpl implements ExaminationPayload {
    private final ExaminationRepository   conduiteRepository;
    private final ExaminationRepositories conduiteRepositories;
    @Override
    public
    Mono<String> getCode ( ){
        return conduiteRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("EXN100000000001");
            }
            else {
                Mono<String> code = conduiteRepositories.getLastExamination()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return ExaminationCode.generate(code);
            }
        });
    }
    @Override
    public
    Mono<Boolean> isExaminationCodeExist ( String ExaminationCode ) {
        return conduiteRepository.existsExaminationByCode (ExaminationCode);
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
    public Mono<Boolean> verifyExamCodeAndStudent(String examCode, String studentCode) {
        return conduiteRepository.existsByExamCodeAndStudentCode(examCode, studentCode);
    }

}
