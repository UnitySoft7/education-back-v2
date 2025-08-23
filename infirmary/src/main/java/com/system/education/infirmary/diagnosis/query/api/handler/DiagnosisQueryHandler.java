package com.system.education.infirmary.diagnosis.query.api.handler;

import com.system.education.infirmary.consumed.query.api.query.ConsumedByCodeQuery;
import com.system.education.infirmary.diagnosis.query.api.query.DiagnosisByCodeAndSemesterQuery;
import com.system.education.infirmary.diagnosis.query.api.response.DiagnosisResponse;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DiagnosisQueryHandler {
    Flux<DiagnosisResponse> getDiagnosis();
    Mono<DiagnosisResponse> getDiagnosisById(ProductByIdQuery query);
    Flux<DiagnosisResponse> getDiagnosisByStudentCode(ConsumedByCodeQuery query);
    Flux<DiagnosisResponse> getDiagnosisByEstablishment(ConsumedByCodeQuery query);
    Mono<DiagnosisResponse> getDiagnosisByStudentAndSemester(DiagnosisByCodeAndSemesterQuery query);
    Flux<DiagnosisResponse> getDiagnosisByEstablishmentAndSemester(DiagnosisByCodeAndSemesterQuery query);
}
