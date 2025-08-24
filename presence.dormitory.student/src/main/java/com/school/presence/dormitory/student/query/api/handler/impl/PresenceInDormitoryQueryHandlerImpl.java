package com.school.presence.dormitory.student.query.api.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.presence.dormitory.student.core.model.PresenceInDormitory;
import com.school.presence.dormitory.student.query.api.handler.PresenceInDormitoryQueryHandler;
import com.school.presence.dormitory.student.query.api.repository.PresenceInDormitoryRepository;
import com.school.presence.dormitory.student.query.api.response.PresenceInDormitoryResponse;
import com.school.presence.dormitory.student.query.api.response.PresenceQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PresenceInDormitoryQueryHandlerImpl implements PresenceInDormitoryQueryHandler {
    private final PresenceInDormitoryRepository presenceInDormitoryRepository;
    private final ObjectMapper objectMapper;


    @Override
    public Flux<PresenceInDormitoryResponse> findPresenceInDormitorys() {
        return presenceInDormitoryRepository.findAll().map(this::getPresenceInDormitory);
    }

    @Override
    public Mono<PresenceInDormitoryResponse> findPresenceInDormitoryByPresenceInDormitoryCode(String code) {
        return presenceInDormitoryRepository.findPresenceInDormitoryByPresenceInDormitoryCode(code).map(this::getPresenceInDormitory);
    }

//    @Override
    public Flux<PresenceQueryResponse> findByESCS(String escs) {
        return presenceInDormitoryRepository.countByEscs(escs)
                .map((java.util.function.Function<? super PresenceQueryResponse, ? extends PresenceQueryResponse>) this::mapToResponse);
    }

    private PresenceQueryResponse mapToResponse(PresenceQueryResponse presenceQueryResponse) {
        return new PresenceQueryResponse(
                presenceQueryResponse.ESCS(),
                presenceQueryResponse.studentFullname(),
                presenceQueryResponse.status()
        );
    }

    private PresenceInDormitoryResponse getPresenceInDormitory(PresenceInDormitory presenceInDormitory) {
        List<PresenceQueryResponse> escsList = new ArrayList<>();
        try {
            escsList = objectMapper.readValue(presenceInDormitory.getPresence(), new TypeReference<List<PresenceQueryResponse>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // ou log
        }
        return new PresenceInDormitoryResponse(presenceInDormitory.getPresenceInDormitoryId(),
                presenceInDormitory.getPresenceInDormitoryCode(),presenceInDormitory.getPresenceInDormitoryCode(),
                escsList,
                presenceInDormitory.getProf(),
                presenceInDormitory.getEffective(), presenceInDormitory.getAbsents(),
                presenceInDormitory.getPresents(), presenceInDormitory.getSchoolYear(),
                presenceInDormitory.getTrimester(), presenceInDormitory.getStatus(),
                presenceInDormitory.getLogCreatedAt(), presenceInDormitory.getLogCreatedDate(),
                presenceInDormitory.getLogCreatedMonth(), presenceInDormitory.getLogCreatedYear());
    }
}


