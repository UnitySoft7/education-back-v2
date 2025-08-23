package com.school.bed.query.api.handler.impl;
import com.school.bed.cmd.api.command.BedCreatedCommand;
import com.school.bed.cmd.api.command.BedUpdatedCommand;
import com.school.bed.core.model.Bed;
import com.school.bed.core.payload.BedPayload;
import com.school.bed.query.api.handler.BedEventHandler;
import com.school.bed.query.api.repository.BedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class BedEventHandlerImpl implements BedEventHandler {
    private final BedRepository bedRepository;
    private final BedPayload bedPayload;
    @Override
    public Mono<Bed> create(BedCreatedCommand command) {
        return bedPayload.getCode().flatMap(code -> {
            Bed value = Bed.builder().bedId(UUID.randomUUID().toString()).sigle(command.sigle()).code(code).build();
            return bedRepository.save(value);
        });
    }
    @Override
    public Mono<Bed> update(BedUpdatedCommand command) {
        return bedRepository.findBedByCode(command.bedCode()).switchIfEmpty(Mono.error(new IllegalArgumentException("Bed not found"))).flatMap(existingBed -> bedRepository.findBedBySigle(command.sigle()).filter(other -> !other.getCode().equals(existingBed.getCode())).flatMap(conflict -> Mono.error(new IllegalArgumentException("Bed name already in use"))).then(Mono.defer(() -> {
            existingBed.setSigle(command.sigle());
            return bedRepository.save(existingBed);
        })));
    }
}
