package com.school.section.query.api.handler;

import com.school.section.core.model.Section;
import com.school.section.query.api.response.SectionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface SectionQueryHandler {
    Flux<SectionResponse> findSections ( );

    Mono<SectionResponse> findSectionById ( String clothId );

    Mono<SectionResponse> findSectionBySectionCode ( String clothCode );

    Flux<SectionResponse> findSectionByEstablishmentCode(String establishmentCode);

    SectionResponse getSection(Section Section );
}