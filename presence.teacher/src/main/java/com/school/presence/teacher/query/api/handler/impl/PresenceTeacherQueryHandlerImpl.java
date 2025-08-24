package com.school.presence.teacher.query.api.handler.impl;

import com.school.presence.teacher.core.model.PresenceTeacher;
import com.school.presence.teacher.query.api.handler.PresenceTeacherQueryHandler;
import com.school.presence.teacher.query.api.repository.PresenceTeacherRepository;
import com.school.presence.teacher.query.api.response.PresenceTeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PresenceTeacherQueryHandlerImpl implements PresenceTeacherQueryHandler {
    private final PresenceTeacherRepository clothRepository;

    @Override
    public Flux<PresenceTeacherResponse> findPresenceTeachers() {
        return clothRepository.findAll().map(this::getPresenceTeacher);
    }

    @Override
    public Mono<PresenceTeacherResponse> findPresenceTeacherById(String clothId) {
        return clothRepository.findById(clothId).map(this::getPresenceTeacher);
    }

    @Override
    public Mono<PresenceTeacherResponse> findPresenceTeacherByPresenceTeacherCode(String clothCode) {
        return clothRepository.findPresenceTeacherByCode(clothCode).map(this::getPresenceTeacher);
    }

    private PresenceTeacherResponse getPresenceTeacher(PresenceTeacher presenceTeacher) {
        return new PresenceTeacherResponse(presenceTeacher.getPresenceTeacherId(),
                presenceTeacher.getCode(), presenceTeacher.getProf(),
                presenceTeacher.getProfName(), presenceTeacher.getPointer(),
                presenceTeacher.getPointerName(), presenceTeacher.getDepartHour(),
                presenceTeacher.getDay(), presenceTeacher.getTrimester(),
                presenceTeacher.getSchoolYear(), presenceTeacher.getLogCreatedAt(),
                presenceTeacher.getLogCreatedDate(), presenceTeacher.getLogCreatedMonth(),
                presenceTeacher.getLogCreatedYear());
    }
}
