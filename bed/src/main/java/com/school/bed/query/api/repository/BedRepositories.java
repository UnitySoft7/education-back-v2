package com.school.bed.query.api.repository;

import com.school.bed.core.model.Bed;
import reactor.core.publisher.Mono;

public interface BedRepositories {
    Mono<Bed> getLastBed();
}
