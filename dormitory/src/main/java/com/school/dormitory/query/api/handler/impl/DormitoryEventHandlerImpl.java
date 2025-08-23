package com.school.dormitory.query.api.handler.impl;

import com.school.dormitory.cmd.api.command.DormitoryCreatedCommand;
import com.school.dormitory.cmd.api.command.DormitoryUpdatedCommand;
import com.school.dormitory.core.common.LogCreated;
import com.school.dormitory.core.model.Dormitory;
import com.school.dormitory.core.payload.DormitoryPayload;
import com.school.dormitory.query.api.handler.DormitoryEventHandler;
import com.school.dormitory.query.api.repository.DormitoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class DormitoryEventHandlerImpl implements DormitoryEventHandler {
    private final  DormitoryRepository dormitoryRepository;
    private final  DormitoryPayload  dormitoryPayload;
    @Override
    public
    Mono<Dormitory> create ( DormitoryCreatedCommand command ) {
       return dormitoryPayload.getCode().flatMap(code -> {
                        Dormitory value = Dormitory.builder()
                                .dormitoryId (UUID.randomUUID().toString())
                                .name ( command.dormitoryName () )
                                .code ( code )
                                .schoolYear("2025-2026")
                                .category(command.category())
                                .logCreatedAt(LogCreated.At())
                                .logCreatedDate(LogCreated.Date())
                                .logCreatedMonth(LogCreated.Month())
                                .logCreatedYear(LogCreated.Year())
                                .build();
                        return dormitoryRepository.save(value);
                    });
                }

    @Override
    public Mono<Dormitory> update(DormitoryUpdatedCommand command) {
        return dormitoryRepository.findDormitoryByCode(command.dormitoryCode())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Dormitory not found")))
                .flatMap(existingDormitory ->
                        dormitoryRepository.findDormitoryByName(command.dormitoryName())
                                .filter(other -> !other.getCode().equals(existingDormitory.getCode()))
                                .flatMap(conflict -> Mono.error(new IllegalArgumentException("Dormitory name already in use")))
                                .then(Mono.defer(() -> {
                                    existingDormitory.setName(command.dormitoryName());
                                    existingDormitory.setCategory(command.category());
                                    return dormitoryRepository.save(existingDormitory);
                                }))
                );
    }

}
