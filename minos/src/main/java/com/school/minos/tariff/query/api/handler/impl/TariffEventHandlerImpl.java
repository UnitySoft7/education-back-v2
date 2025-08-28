package com.school.minos.tariff.query.api.handler.impl;

import com.school.minos.core.common.LogCreated;
import com.school.minos.tariff.cmd.api.command.TariffCreatedCommand;
import com.school.minos.tariff.core.model.Tariff;
import com.school.minos.tariff.core.payload.TariffPayload;
import com.school.minos.tariff.query.api.handler.TariffEventHandler;
import com.school.minos.tariff.query.api.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TariffEventHandlerImpl implements TariffEventHandler {
    private final TariffRepository tariffRepository;
    private final TariffPayload tariffPayload;

    @Override
    public Mono<Tariff> create(TariffCreatedCommand command) {
        return tariffPayload.getCode().flatMap(code -> {
            Tariff value = Tariff.builder()
                    .tariffId(UUID.randomUUID().toString())
                    .tariffCode(code)
                    .amount(command.amount())
                    .establishmentName(command.establishmentName())
                    .establishmentCode(command.establishmentCode())
                    .sectionName(command.sectionName())
                    .sectionCode(command.sectionCode())
                    .classroomName(command.className())
                    .classroomCode(command.classCode())
                    .logCreatedAt(LogCreated.At())
                    .logCreatedDate(LogCreated.Date())
                    .logCreatedMonth(LogCreated.Month())
                    .logCreatedYear(LogCreated.Year())
                    .build();
            return tariffRepository.save(value);
        });
    }
}
