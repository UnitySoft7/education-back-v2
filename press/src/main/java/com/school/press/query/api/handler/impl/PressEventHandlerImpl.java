package com.school.press.query.api.handler.impl;
import com.school.press.cmd.api.command.PressCreatedCommand;
import com.school.press.cmd.api.command.PressUpdatedCommand;
import com.school.press.core.model.Press;
import com.school.press.core.payload.PressPayload;
import com.school.press.query.api.handler.PressEventHandler;
import com.school.press.query.api.repository.PressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PressEventHandlerImpl implements PressEventHandler {
    private final PressRepository pressRepository;
    private final PressPayload pressPayload;
    @Override
    public Mono<Press> create(PressCreatedCommand command) {
        return pressPayload.getCode().flatMap(code -> {
            Press value = Press.builder().pressId(UUID.randomUUID().toString()).sigle(command.sigle()).code(code).build();
            return pressRepository.save(value);
        });
    }
    @Override
    public Mono<Press> update(PressUpdatedCommand command) {
        return pressRepository.findPressByCode(command.pressCode()).switchIfEmpty(Mono.error(new IllegalArgumentException("Press not found"))).flatMap(existingPress -> pressRepository.findPressBySigle(command.sigle()).filter(other -> !other.getCode().equals(existingPress.getCode())).flatMap(conflict -> Mono.error(new IllegalArgumentException("Press name already in use"))).then(Mono.defer(() -> {
            existingPress.setSigle(command.sigle());
            return pressRepository.save(existingPress);
        })));
    }
}
