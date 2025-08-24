package com.school.presence.student.query.api.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.presence.student.core.model.PresenceInClass;
import com.school.presence.student.query.api.handler.PresenceInClassQueryHandler;
import com.school.presence.student.query.api.repository.PresenceInClassRepository;
import com.school.presence.student.query.api.response.PresenceInClassResponse;
import com.school.presence.student.query.api.response.PresenceQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PresenceInClassQueryHandlerImpl implements PresenceInClassQueryHandler {
    private final PresenceInClassRepository presenceInClassRepository;
    private final ObjectMapper objectMapper;


    @Override
    public Flux<PresenceInClassResponse> findPresenceInClasss() {
        return presenceInClassRepository.findAll().map(this::getPresenceInClass);
    }

    @Override
    public Mono<PresenceInClassResponse> findPresenceInClassByPresenceInClassCode(String code) {
        return presenceInClassRepository.findPresenceInClassByPresenceInClassCode(code).map(this::getPresenceInClass);
    }

//    @Override
    public Flux<PresenceQueryResponse> findByESCS(String escs) {
        return presenceInClassRepository.countByEscs(escs)
                .map((java.util.function.Function<? super PresenceQueryResponse, ? extends PresenceQueryResponse>) this::mapToResponse);
    }

    private PresenceQueryResponse mapToResponse(PresenceQueryResponse presenceQueryResponse) {
        return new PresenceQueryResponse(
                presenceQueryResponse.ESCS(),
                presenceQueryResponse.studentFullname(),
                presenceQueryResponse.status()
        );
    }

    private PresenceInClassResponse getPresenceInClass(PresenceInClass presenceInClass) {
        List<PresenceQueryResponse> escsList = new ArrayList<>();
        try {
            escsList = objectMapper.readValue(presenceInClass.getPresence(), new TypeReference<List<PresenceQueryResponse>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // ou log
        }
        return new PresenceInClassResponse(presenceInClass.getPresenceInClassId(),
                presenceInClass.getPresenceInClassCode(),
                escsList,
                presenceInClass.getPointer(), presenceInClass.getScheduleCode(),
                presenceInClass.getEffective(), presenceInClass.getAbsents(),
                presenceInClass.getPresents(), presenceInClass.getSchoolYear(),
                presenceInClass.getTrimester(), presenceInClass.getStatus(),
                presenceInClass.getLogCreatedAt(), presenceInClass.getLogCreatedDate(),
                presenceInClass.getLogCreatedMonth(), presenceInClass.getLogCreatedYear());
    }
}


