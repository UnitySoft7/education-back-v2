package com.system.education.cafeteria.consumption.query.api.handler.impl;

import com.system.education.cafeteria.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.cafeteria.consumption.core.model.Consumption;
import com.system.education.cafeteria.consumption.query.api.handler.ConsumptionQueryHandler;
import com.system.education.cafeteria.consumption.query.api.query.ConsumedByCodeAndSemesterQuery;
import com.system.education.cafeteria.consumption.query.api.repository.ConsumptionRepository;
import com.system.education.cafeteria.consumption.query.api.response.ConsumptionResponse;
import com.system.education.cafeteria.core.common.Status;
import com.system.education.cafeteria.product.query.api.query.ProductByCodeQuery;
import com.system.education.cafeteria.product.query.api.query.ProductByIdQuery;
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
     * @param consumption the product to convert
     * @return the driver response
     */
    private Mono<ConsumptionResponse> getConsumptionResponse(Consumption consumption) {
        return Mono.just(
                new ConsumptionResponse(consumption.getConsumptionId(),
                        consumption.getStudentCode(), consumption.getStudentName(),
                        consumption.getConsumptionAmount(), consumption.getPayedAmount(),
                        consumption.getStatus(), consumption.getEstablishmentCode(),
                        consumption.getEstablishmentName(), consumption.getSemester(),
                        consumption.getSchoolYear(), consumption.getLogCreatedAt(),
                        consumption.getLogCreatedDate(), consumption.getLogCreatedMonth(),
                        consumption.getLogCreatedYear()
                )
        );
    }
}
