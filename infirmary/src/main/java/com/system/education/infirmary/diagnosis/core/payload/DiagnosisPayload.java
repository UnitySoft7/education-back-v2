package com.system.education.infirmary.diagnosis.core.payload;

import reactor.core.publisher.Mono;

public interface DiagnosisPayload {
    Mono<String> getCode();
}
