package com.system.education.infirmary.diagnosis.core.payload.impl;

import com.system.education.infirmary.core.common.DiagnosisCode;
import com.system.education.infirmary.diagnosis.core.payload.DiagnosisPayload;
import com.system.education.infirmary.diagnosis.query.api.repository.DiagnosisRepositories;
import com.system.education.infirmary.diagnosis.query.api.repository.DiagnosisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DiagnosisPayloadImpl implements DiagnosisPayload {
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisRepositories diagnosisRepositories;

    /**
     * This method generates a unique code for the diagnosis.
     * It first checks if there are any existing diagnosis in the repository.
     * If there are no diagnosis, it returns a default code "ESIDC10000001".
     * If there are existing diagnosis, it retrieves the last diagnosis's code and generates a new code based on it.
     *
     * @return A Mono containing the generated diagnosis code.
     */
    @Override
    public Mono<String> getCode(){
        return diagnosisRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESIDC10000001");
            }
            else {
                Mono<String> code = diagnosisRepositories.getLastDiagnosis()
                        .flatMap(diagnosis -> Mono.just(diagnosis.getDiagnosisCode()));
                return DiagnosisCode.generate(code);
            }
        });
    }
}
