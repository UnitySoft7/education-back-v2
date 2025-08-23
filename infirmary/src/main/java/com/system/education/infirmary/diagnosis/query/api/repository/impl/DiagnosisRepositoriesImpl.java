package com.system.education.infirmary.diagnosis.query.api.repository.impl;

import com.system.education.infirmary.diagnosis.query.api.repository.DiagnosisRepositories;
import com.system.education.infirmary.diagnosis.core.model.Diagnosis;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class DiagnosisRepositoriesImpl implements DiagnosisRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public DiagnosisRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Diagnosis> getLastDiagnosis() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "diagnosisCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Diagnosis.class)
                .next();
    }
}
