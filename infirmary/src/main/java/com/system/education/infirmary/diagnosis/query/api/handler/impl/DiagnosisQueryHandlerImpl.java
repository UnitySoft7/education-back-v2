package com.system.education.infirmary.diagnosis.query.api.handler.impl;

import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.infirmary.diagnosis.core.model.Diagnosis;
import com.system.education.infirmary.diagnosis.query.api.handler.DiagnosisQueryHandler;
import com.system.education.infirmary.core.common.Status;
import com.system.education.infirmary.diagnosis.query.api.query.DiagnosisByCodeAndSemesterQuery;
import com.system.education.infirmary.diagnosis.query.api.repository.DiagnosisRepository;
import com.system.education.infirmary.diagnosis.query.api.response.DiagnosisResponse;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DiagnosisQueryHandlerImpl implements DiagnosisQueryHandler {
    private final DiagnosisRepository diagnosisRepository;

    /**
     * This method is used to get all diagnosis
     * @return a flux of diagnosis response
     */
    @Override
    public Flux<DiagnosisResponse> getDiagnosis() {
        return diagnosisRepository.findAll()
                .flatMap(this::getDiagnosisResponse);
    }

    /**
     * This method is used to get a diagnosis by ID
     * @param query the ID of the diagnosis
     * @return a mono of diagnosis response
     */
    @Override
    public Mono<DiagnosisResponse> getDiagnosisById(ProductByIdQuery query) {
        return diagnosisRepository.findById(query.id())
                .flatMap(this::getDiagnosisResponse);
    }

    /**
     * This method is used to get a diagnosis by student code
     * @param query the student code of the student
     * @return a mono of diagnosis response
     */
    @Override
    public Flux<DiagnosisResponse> getDiagnosisByStudentCode(ConsumedByCodeQuery query) {
        return diagnosisRepository.findByStudentCodeAndSchoolYear(query.code(), query.schoolYear())
                .flatMap(this::getDiagnosisResponse);
    }

    /**
     * This method is used to get a diagnosis by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of diagnosis response
     */
    @Override
    public Flux<DiagnosisResponse> getDiagnosisByEstablishment(ConsumedByCodeQuery query) {
        return diagnosisRepository.findByEstablishmentCodeAndSchoolYear(query.code(), query.schoolYear())
                .flatMap(this::getDiagnosisResponse);
    }

    /**
     * This method is used to get a diagnosis by student code and semester
     * @param query the student code and semester of the product
     * @return a mono of diagnosis response
     */
    @Override
    public Mono<DiagnosisResponse> getDiagnosisByStudentAndSemester(DiagnosisByCodeAndSemesterQuery query) {
        return diagnosisRepository.findByStudentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getDiagnosisResponse);
    }

    /**
     * This method is used to get a diagnosis by establishment code and semester
     * @param query the establishment code and semester of the establishment
     * @return a mono of diagnosis response
     */
    @Override
    public Flux<DiagnosisResponse> getDiagnosisByEstablishmentAndSemester(DiagnosisByCodeAndSemesterQuery query) {
        return diagnosisRepository.findByEstablishmentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getDiagnosisResponse);
    }

    /**
     * This method is used to convert a diagnosis to a diagnosis response
     * @param diagnosis the product to convert
     * @return the driver response
     */
    private Mono<DiagnosisResponse> getDiagnosisResponse(Diagnosis diagnosis) {
        return Mono.just(
                new DiagnosisResponse(diagnosis.getDiagnosisId(), diagnosis.getDiagnosisCode(),
                        diagnosis.getStudentCode(), diagnosis.getStudentName(),
                        diagnosis.getCondition(), diagnosis.getComment(),
                        diagnosis.getEstablishmentCode(), diagnosis.getEstablishmentName(),
                        diagnosis.getSemester(), diagnosis.getSchoolYear(),
                        diagnosis.getLogCreatedAt(), diagnosis.getLogCreatedDate(),
                        diagnosis.getLogCreatedMonth(), diagnosis.getLogCreatedYear()
                )
        );
    }
}
