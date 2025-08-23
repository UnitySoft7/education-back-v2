package com.system.education.establishment.query.api.handler;

import com.system.education.establishment.query.api.query.EstablishmentByCodeQuery;
import com.system.education.establishment.query.api.query.EstablishmentByIdQuery;
import com.system.education.establishment.query.api.response.EstablishmentResponse;
import com.system.education.establishment.query.api.response.SemesterResponse;
import org.springframework.core.io.UrlResource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EstablishmentQueryHandler {
    Flux<EstablishmentResponse> getEstablishments();
    Mono<EstablishmentResponse> getEstablishmentById(EstablishmentByIdQuery query);
    Mono<EstablishmentResponse> getEstablishmentByCode(EstablishmentByCodeQuery query);

    Mono<UrlResource> getLogoByCode(EstablishmentByCodeQuery query);

    List<SemesterResponse> getSemesters();
}
