package com.system.education.infirmary.consumed.query.api.handler;

import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeAndSemesterQuery;
import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.infirmary.consumed.query.api.query.ConsumedByDiagnosisCodeQuery;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
import com.system.education.infirmary.consumed.query.api.response.ConsumedResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConsumedQueryHandler {
    Flux<ConsumedResponse> getConsumed();
    Mono<ConsumedResponse> getConsumedById(ProductByIdQuery query);
    Flux<ConsumedResponse> getConsumedByStudentCode(ConsumedByCodeQuery query);
    Flux<ConsumedResponse> getConsumedByEstablishment(ConsumedByCodeQuery query);

    Flux<ConsumedResponse> getConsumedByStudentAndSemester(ConsumedByCodeAndSemesterQuery query);

    Flux<ConsumedResponse> getConsumedByEstablishmentAndSemester(ConsumedByCodeAndSemesterQuery query);

    Flux<ConsumedResponse> getConsumedByStudentAndSemesterAndStatus(ConsumedByCodeAndSemesterQuery query);

    Flux<ConsumedResponse> getConsumedByEstablishmentAndSemesterAndStatus(ConsumedByCodeAndSemesterQuery query);

    Flux<ConsumedResponse> getConsumedByDiagnosis(ConsumedByDiagnosisCodeQuery query);
}
