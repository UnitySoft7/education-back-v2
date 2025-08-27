package com.school.minos.minos.query.api.handler.impl;

import com.school.minos.minos.core.model.Minos;
import com.school.minos.minos.query.api.handler.MinosQueryHandler;
import com.school.minos.minos.query.api.repository.MinosRepository;
import com.school.minos.minos.query.api.response.MinosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MinosQueryHandlerImpl implements MinosQueryHandler {
    private final MinosRepository minosRepository;

    @Override
    public Flux<MinosResponse> findMinos() {
        return minosRepository.findAll().map(this::getMinos);
    }

    @Override
    public Mono<MinosResponse> findMinosByMinosCode(String code) {
        return minosRepository.findMinosByMinosCode(code).map(this::getMinos);
    }

    @Override
    public MinosResponse getMinos(Minos minos) {
        return new MinosResponse(minos.getMinosId(),
                minos.getMinosCode(),
                minos.getStudentCode(),
                minos.getStudentFullname(),
                minos.getSemester(),
                minos.getAmount(),
                minos.getMaxamount(),
                minos.getStatus(),
                minos.getSchoolYear(),
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
