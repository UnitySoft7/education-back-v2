package com.school.minos.tariff.query.api.handler.impl;

import com.school.minos.tariff.core.model.Tariff;
import com.school.minos.tariff.query.api.handler.TariffQueryHandler;
import com.school.minos.tariff.query.api.repository.TariffRepository;
import com.school.minos.tariff.query.api.response.TariffResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TariffQueryHandlerImpl implements TariffQueryHandler {
    private final TariffRepository minosRepository;

    @Override
    public Flux<TariffResponse> findTariffs() {
        return minosRepository.findAll().map(this::getTariff);
    }

    @Override
    public Mono<TariffResponse> findTariffByTariffCode(String code) {
        return minosRepository.findTariffByTariffCode(code).map(this::getTariff);
    }

    @Override
    public Mono<TariffResponse> findTariffByTariffId(String tariffId) {
        return minosRepository.findById(tariffId).map(this::getTariff);
    }

    @Override
    public Mono<TariffResponse> findTariffByClassCode(String code) {
        return minosRepository.findTariffByClassroomCode(code).map(this::getTariff);
    }

    @Override
    public Flux<TariffResponse> findTariffByEstablishment(String code) {
        return minosRepository.findTariffByEstablishmentCode(code).map(this::getTariff);
    }

    public TariffResponse getTariff(Tariff minos) {
        return new TariffResponse(
                minos.getTariffId(),
                minos.getTariffCode(),
                minos.getAmount(),
                minos.getEstablishmentName(),
                minos.getEstablishmentCode(),
                minos.getSectionName(),
                minos.getSectionCode(),
                minos.getClassroomName(),
                minos.getClassroomCode(),
                minos.getLogCreatedAt(),
                minos.getLogCreatedDate(),
                minos.getLogCreatedMonth(),
                minos.getLogCreatedYear());
    }
}
