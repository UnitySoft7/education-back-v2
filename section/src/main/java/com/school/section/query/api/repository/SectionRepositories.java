package com.school.section.query.api.repository;

import com.school.section.core.model.Section;
import reactor.core.publisher.Mono;

public
interface SectionRepositories {
    Mono<Section> getLastSection ( );
}
