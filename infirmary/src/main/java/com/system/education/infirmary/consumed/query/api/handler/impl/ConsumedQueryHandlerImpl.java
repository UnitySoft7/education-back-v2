package com.system.education.infirmary.consumed.query.api.handler.impl;

import com.system.education.infirmary.consumed.core.model.InfirmaryConsumed;
import com.system.education.infirmary.consumed.query.api.handler.ConsumedQueryHandler;
import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeAndSemesterQuery;
import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.infirmary.consumed.query.api.query.ConsumedByDiagnosisCodeQuery;
import com.system.education.infirmary.core.common.Status;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
import com.system.education.infirmary.consumed.query.api.repository.ConsumedRepository;
import com.system.education.infirmary.consumed.query.api.response.ConsumedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConsumedQueryHandlerImpl implements ConsumedQueryHandler {
    private final ConsumedRepository consumedRepository;

    /**
     * This method is used to get all consumed
     * @return a flux of consumed response
     */
    @Override
    public Flux<ConsumedResponse> getConsumed() {
        return consumedRepository.findAll()
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to get a consumed by ID
     * @param query the ID of the consumed
     * @return a mono of consumed response
     */
    @Override
    public Mono<ConsumedResponse> getConsumedById(ProductByIdQuery query) {
        return consumedRepository.findById(query.id())
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to get a consumed by student code
     * @param query the student code of the student
     * @return a mono of consumed response
     */
    @Override
    public Flux<ConsumedResponse> getConsumedByStudentCode(ConsumedByCodeQuery query) {
        return consumedRepository.findByStudentCodeAndSchoolYear(query.code(), query.schoolYear())
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to get a consumed by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of consumed response
     */
    @Override
    public Flux<ConsumedResponse> getConsumedByEstablishment(ConsumedByCodeQuery query) {
        return consumedRepository.findByEstablishmentCodeAndSchoolYear(query.code(), query.schoolYear())
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to get a consumed by student code and semester
     * @param query the student code and semester of the product
     * @return a mono of consumed response
     */
    @Override
    public Flux<ConsumedResponse> getConsumedByStudentAndSemester(ConsumedByCodeAndSemesterQuery query) {
        return consumedRepository.findByStudentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to get a consumed by establishment code and semester
     * @param query the establishment code and semester of the establishment
     * @return a mono of consumed response
     */
    @Override
    public Flux<ConsumedResponse> getConsumedByEstablishmentAndSemester(ConsumedByCodeAndSemesterQuery query) {
        return consumedRepository.findByEstablishmentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to get a consumed by student code and semester and status
     * @param query the student code and semester and status of the product
     * @return a mono of consumed response
     */
    @Override
    public Flux<ConsumedResponse> getConsumedByStudentAndSemesterAndStatus(ConsumedByCodeAndSemesterQuery query) {
        return consumedRepository.findByStudentCodeAndSemesterAndStatusAndSchoolYear(query.code(), query.semester(), Status.not_payed(), query.schoolYear())
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to get a consumed by establishment code and semester and status
     * @param query the establishment code and semester and status of the establishment
     * @return a mono of consumed response
     */
    @Override
    public Flux<ConsumedResponse> getConsumedByEstablishmentAndSemesterAndStatus(ConsumedByCodeAndSemesterQuery query) {
        return consumedRepository.findByEstablishmentCodeAndSemesterAndStatusAndSchoolYear(query.code(), query.semester(), Status.not_payed(), query.schoolYear())
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to get a consumed by diagnosis code
     * @param query the diagnosis code of the diagnosis
     * @return a mono of consumed response
     */
    @Override
    public Flux<ConsumedResponse> getConsumedByDiagnosis(ConsumedByDiagnosisCodeQuery query) {
        return consumedRepository.findByDiagnosisCode(query.code())
                .flatMap(this::getConsumedResponse);
    }

    /**
     * This method is used to convert a consumed to a consumed response
     * @param infirmaryConsumed the product to convert
     * @return the driver response
     */
    private Mono<ConsumedResponse> getConsumedResponse(InfirmaryConsumed infirmaryConsumed) {
        return Mono.just(
                new ConsumedResponse(infirmaryConsumed.getConsumedId(),
                        infirmaryConsumed.getProductCode(), infirmaryConsumed.getProductName(),
                        infirmaryConsumed.getQty(), infirmaryConsumed.getAmount(),
                        infirmaryConsumed.getStudentCode(), infirmaryConsumed.getStudentName(),
                        infirmaryConsumed.getEmployeeCode(), infirmaryConsumed.getEmployeeName(),
                        infirmaryConsumed.getEstablishmentCode(), infirmaryConsumed.getEstablishmentName(),
                        infirmaryConsumed.getSemester(), infirmaryConsumed.getSchoolYear(), infirmaryConsumed.getStatus(),
                        infirmaryConsumed.getLogCreatedAt(), infirmaryConsumed.getLogCreatedDate(),
                        infirmaryConsumed.getLogCreatedMonth(), infirmaryConsumed.getLogCreatedYear()
                )
        );
    }
}
