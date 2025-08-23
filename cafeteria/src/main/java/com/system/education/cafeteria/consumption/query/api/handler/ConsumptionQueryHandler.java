package com.system.education.cafeteria.consumption.query.api.handler;

import com.system.education.cafeteria.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.cafeteria.consumption.query.api.query.ConsumedByCodeAndSemesterQuery;
import com.system.education.cafeteria.consumption.query.api.response.ConsumptionResponse;
import com.system.education.cafeteria.product.query.api.query.ProductByCodeQuery;
import com.system.education.cafeteria.product.query.api.query.ProductByIdQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConsumptionQueryHandler {
    Flux<ConsumptionResponse> getConsumption();
    Mono<ConsumptionResponse> getConsumptionById(ProductByIdQuery query);
    Flux<ConsumptionResponse> getConsumptionByStudentCode(ConsumedByCodeQuery query);
    Flux<ConsumptionResponse> getConsumptionByEstablishment(ConsumedByCodeQuery query);

    Mono<ConsumptionResponse> getConsumptionByStudentAndSemester(ConsumedByCodeAndSemesterQuery query);

    Flux<ConsumptionResponse> getConsumptionByEstablishmentAndSemester(ConsumedByCodeAndSemesterQuery query);

    Flux<ConsumptionResponse> getConsumptionByEstablishmentAndSemesterAndStatus(ConsumedByCodeAndSemesterQuery query);
}
