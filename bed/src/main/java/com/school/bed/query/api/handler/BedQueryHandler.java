package com.school.bed.query.api.handler;

import com.school.bed.core.model.Bed;
import com.school.bed.query.api.response.BedResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BedQueryHandler {


    Flux<BedResponse> findBeds();

    Mono<BedResponse> findBedById(String clothId);

    Mono<BedResponse> findBedByBedCode(String clothCode);

    BedResponse getBed(Bed Bed);
}