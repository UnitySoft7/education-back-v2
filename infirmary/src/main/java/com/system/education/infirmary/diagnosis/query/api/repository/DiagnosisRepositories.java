package com.system.education.infirmary.diagnosis.query.api.repository;

import com.system.education.infirmary.diagnosis.core.model.Diagnosis;
import reactor.core.publisher.Mono;

public interface DiagnosisRepositories {
    Mono<Diagnosis> getLastDiagnosis();
}
