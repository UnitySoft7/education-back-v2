package com.school.section.query.api.handler.impl;

import com.school.section.core.model.Section;
import com.school.section.query.api.handler.SectionQueryHandler;
import com.school.section.query.api.repository.SectionRepository;
import com.school.section.query.api.response.SectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SectionQueryHandlerImpl implements SectionQueryHandler {
    private final SectionRepository clothRepository;

    @Override
    public Flux<SectionResponse> findSections() {
        return clothRepository.findAll()
                .map(this::getSection);
    }

    @Override
    public Mono<SectionResponse> findSectionById(String clothId) {
        return clothRepository.findById(clothId)
                .map(this::getSection);
    }

    @Override
    public Mono<SectionResponse> findSectionBySectionCode(String clothCode) {
        return clothRepository.findSectionBySectionCode(clothCode)
                .map(this::getSection);
    }

    @Override
    public Flux<SectionResponse> findSectionByEstablishmentCode(String establishmentCode) {
        return clothRepository.findSectionByEstablishmentCode(establishmentCode)
                .map(this::getSection);
    }

    @Override
    public SectionResponse getSection(Section section) {
        return new SectionResponse(
                section.getSectionId(), section.getName(),
                section.getSectionCode(), section.getFrName(),
                section.getEnName(), section.getEstablishmentCode(),
                section.getEstablishmentName()
        );
    }
}
