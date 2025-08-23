package com.school.section.core.payload;

import reactor.core.publisher.Mono;

public
interface SectionPayload {
    Mono<String> getCode ( );

    Mono<Boolean> isSectionNameExist(String sectionName);

    Mono<Boolean> isSectionCodeExist (String SectionCode );
}