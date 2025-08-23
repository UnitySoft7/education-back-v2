package com.school.minos.tariff.query.api.controller;

import com.school.minos.tariff.query.api.dto.AllLookupTariffResponse;
import com.school.minos.tariff.query.api.dto.LookupTariffResponse;
import com.school.minos.tariff.query.api.handler.TariffQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/minos/tariff-lookup/")
@Tag(name = "Tariff", description = "Data REST API for tariff resource")
public class TariffLookupController {
    private final TariffQueryHandler tariffQueryHandler;

    @Operation(summary = "Retrieve data tariff")
    @GetMapping(path = "get-tariff", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTariffResponse>> show() {
        return tariffQueryHandler.findTariffs()
                .collectList()
                .map(tariff ->
                        new AllLookupTariffResponse(true, tariff))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data tariff by tariffCode")
    @GetMapping(path = "get-tariff-by-tariff-code/{tariffCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByReceiverCode(@PathVariable("tariffCode") String tariffCode ) {
        return tariffQueryHandler.findTariffByTariffCode(tariffCode)
                .map(tariff ->
                        new LookupTariffResponse (true, tariff))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data tariff by classCode")
    @GetMapping(path = "get-tariff-by-class-code/{classCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByClass(@PathVariable("classCode") String classCode ) {
        return tariffQueryHandler.findTariffByTariffCode(classCode)
                .map(tariff ->
                        new LookupTariffResponse (true, tariff))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data tariff by establishmentCode")
    @GetMapping(path = "get-tariff-by-establishment-code/{establishmentCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupTariffResponse>> getByEstablishment(@PathVariable("establishmentCode") String establishmentCode ) {
        return tariffQueryHandler.findTariffByEstablishment(establishmentCode)
                .collectList()
                .map(tariff ->
                        new AllLookupTariffResponse (true, tariff))
                .map(ResponseEntity::ok);
    }

}
