package com.system.education.infirmary.consumption.query.api.handler.impl;

import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.infirmary.consumption.core.model.InfirmaryConsumption;
import com.system.education.infirmary.consumption.query.api.handler.ConsumptionQueryHandler;
import com.system.education.infirmary.consumption.query.api.query.ConsumedByCodeAndSemesterQuery;
import com.system.education.infirmary.consumption.query.api.repository.ConsumptionRepository;
import com.system.education.infirmary.consumption.query.api.response.ConsumptionResponse;
import com.system.education.infirmary.core.common.Status;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ConsumptionQueryHandlerImpl implements ConsumptionQueryHandler {
    private final ConsumptionRepository consumptionRepository;

    /**
     * This method is used to get all consumption
     * @return a flux of consumption response
     */
    @Override
    public Flux<ConsumptionResponse> getConsumption() {
        return consumptionRepository.findAll()
                .flatMap(this::getConsumptionResponse);
    }

    /**
     * This method is used to get a consumption by ID
     * @param query the ID of the consumption
     * @return a mono of consumption response
     */
    @Override
    public Mono<ConsumptionResponse> getConsumptionById(ProductByIdQuery query) {
        return consumptionRepository.findById(query.id())
                .flatMap(this::getConsumptionResponse);
    }

    /**
     * This method is used to get a consumption by student code
     * @param query the student code of the student
     * @return a mono of consumption response
     */
    @Override
    public Flux<ConsumptionResponse> getConsumptionByStudentCode(ConsumedByCodeQuery query) {
        return consumptionRepository.findByStudentCodeAndSchoolYear(query.code(), query.schoolYear())
                .flatMap(this::getConsumptionResponse);
    }

    /**
     * This method is used to get a consumption by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of consumption response
     */
    @Override
    public Flux<ConsumptionResponse> getConsumptionByEstablishment(ConsumedByCodeQuery query) {
        return consumptionRepository.findByEstablishmentCodeAndSchoolYear(query.code(), query.schoolYear())
                .flatMap(this::getConsumptionResponse);
    }

    /**
     * This method is used to get a consumption by student code and semester
     * @param query the student code and semester of the product
     * @return a mono of consumption response
     */
    @Override
    public Mono<ConsumptionResponse> getConsumptionByStudentAndSemester(ConsumedByCodeAndSemesterQuery query) {
        return consumptionRepository.findByStudentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getConsumptionResponse);
    }

    /**
     * This method is used to get a consumption by establishment code and semester
     * @param query the establishment code and semester of the establishment
     * @return a mono of consumption response
     */
    @Override
    public Flux<ConsumptionResponse> getConsumptionByEstablishmentAndSemester(ConsumedByCodeAndSemesterQuery query) {
        return consumptionRepository.findByEstablishmentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getConsumptionResponse);
    }

    /**
     * This method is used to get a consumption by establishment code and semester and status
     * @param query the establishment code and semester and status of the establishment
     * @return a mono of consumption response
     */
    @Override
    public Flux<ConsumptionResponse> getConsumptionByEstablishmentAndSemesterAndStatus(ConsumedByCodeAndSemesterQuery query) {
        return consumptionRepository.findByEstablishmentCodeAndSemesterAndStatusAndSchoolYear(
                query.code(), query.semester(), Status.not_complete(), query.schoolYear())
                .flatMap(this::getConsumptionResponse);
    }

    /**
     * This method is used to convert a consumption to a consumption response
     * @param infirmaryConsumption the product to convert
     * @return the driver response
     */
    private Mono<ConsumptionResponse> getConsumptionResponse(InfirmaryConsumption infirmaryConsumption) {
        return Mono.just(
                new ConsumptionResponse(infirmaryConsumption.getConsumptionId(),
                        infirmaryConsumption.getStudentCode(), infirmaryConsumption.getStudentName(),
                        infirmaryConsumption.getConsumptionAmount(), infirmaryConsumption.getPayedAmount(),
                        infirmaryConsumption.getStatus(), infirmaryConsumption.getEstablishmentCode(),
                        infirmaryConsumption.getEstablishmentName(), infirmaryConsumption.getSemester(),
                        infirmaryConsumption.getSchoolYear(), infirmaryConsumption.getLogCreatedAt(),
                        infirmaryConsumption.getLogCreatedDate(), infirmaryConsumption.getLogCreatedMonth(),
                        infirmaryConsumption.getLogCreatedYear()
                )
        );
    }
}
