package com.school.section.query.api.repository.impl;

import com.school.section.core.model.Section;
import com.school.section.query.api.repository.SectionRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Repository
public class SectionRepositoriesImpl implements SectionRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Section> getLastSection() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "sectionCode"));
        query.limit(1);
        return reactiveMongoTemplate.find(query, Section.class)
                .next();
    }
}