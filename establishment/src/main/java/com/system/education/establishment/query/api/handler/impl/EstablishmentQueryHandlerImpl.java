package com.system.education.establishment.query.api.handler.impl;

import com.system.education.establishment.core.model.Establishment;
import com.system.education.establishment.query.api.handler.EstablishmentQueryHandler;
import com.system.education.establishment.query.api.query.EstablishmentByCodeQuery;
import com.system.education.establishment.query.api.query.EstablishmentByIdQuery;
import com.system.education.establishment.query.api.repository.EstablishmentRepository;
import com.system.education.establishment.query.api.response.EstablishmentResponse;
import com.system.education.establishment.query.api.response.SemesterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EstablishmentQueryHandlerImpl implements EstablishmentQueryHandler {
    private final EstablishmentRepository establishmentRepository;

    /**
     * This method is used to get all establishments
     * @return a flux of establishment response
     */
    @Override
    public Flux<EstablishmentResponse> getEstablishments() {
        return establishmentRepository.findAll()
                .flatMap(this::getEstablishmentResponse);
    }

    /**
     * This method is used to get an establishment by ID
     * @param query the ID of the establishment
     * @return a mono of establishment response
     */
    @Override
    public Mono<EstablishmentResponse> getEstablishmentById(EstablishmentByIdQuery query) {
        return establishmentRepository.findById(query.id())
                .flatMap(this::getEstablishmentResponse);
    }

    /**
     * This method is used to get an establishment by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of establishment response
     */
    @Override
    public Mono<EstablishmentResponse> getEstablishmentByCode(EstablishmentByCodeQuery query) {
        return establishmentRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getEstablishmentResponse);
    }

    @Override
    public Mono<UrlResource> getLogoByCode(EstablishmentByCodeQuery query) {
        return establishmentRepository.findByEstablishmentCode(query.code())
                .flatMap(establishment -> {
                    Path filePath = Path.of(establishment.getLogoPath());
                    try {
                        return Mono.just(new UrlResource(filePath.toUri()));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * This method is used to convert an establishment to an establishment response
     * @param establishment the establishment to convert
     * @return the driver response
     */
    private Mono<EstablishmentResponse> getEstablishmentResponse(Establishment establishment) {
        return Mono.just(
                new EstablishmentResponse(establishment.getEstablishmentId(),
                        establishment.getEstablishmentCode(), establishment.getEstablishmentName(),
                        establishment.getNickName(), establishment.getNif(), establishment.getRc(),
                        establishment.getPhoneNo(), establishment.getEmail(), establishment.getSite(),
                        establishment.getState(), establishment.getProvince(), establishment.getCommune(),
                        establishment.getZone(), establishment.getQuarter(), establishment.getLocality(),
                        establishment.getLogCreatedAt(), establishment.getLogCreatedMonth(), establishment.
                        getLogCreatedYear(), establishment.getLogCreatedDate(), establishment.getLogModifiedDate())
        );
    }

    /**
     * This method is used to get all trimesters
     * @return a mono of all lookup semester response
     */
    @Override
    public List<SemesterResponse> getSemesters() {
        return List.of(
                new SemesterResponse("S1", "1er semestre", "First term"),
                new SemesterResponse("S2", "2e semestre", "Second term")
        );
    }
}
