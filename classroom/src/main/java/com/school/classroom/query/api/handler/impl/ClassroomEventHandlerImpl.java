package com.school.classroom.query.api.handler.impl;

import com.school.classroom.cmd.api.command.ClassroomCreatedCommand;
import com.school.classroom.cmd.api.command.ClassroomUpdatedCommand;
import com.school.classroom.core.model.Classroom;
import com.school.classroom.core.payload.ClassroomPayload;
import com.school.classroom.query.api.handler.ClassroomEventHandler;
import com.school.classroom.query.api.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ClassroomEventHandlerImpl implements ClassroomEventHandler {
    private final ClassroomRepository classroomRepository;
    private final ClassroomPayload classroomPayload;

    @Override
    public Mono<Classroom> create(ClassroomCreatedCommand command) {
        return classroomPayload.getCode().flatMap(code -> {
            Classroom value = Classroom.builder()
                    .classroomId(UUID.randomUUID().toString())
                    .name(command.classroomName())
                    .code(code)
                    .frName(command.frName())
                    .enName(command.enName())
                    .sectionCode(command.sectionCode())
                    .sectionName(command.sectionName())
                    .establishmentCode(command.establishmentCode())
                    .establishmentName(command.establishmentName())
                    .build();
            return classroomRepository.save(value);
        });
    }

    @Override
    public Mono<Classroom> update(ClassroomUpdatedCommand command) {
        return classroomRepository.findClassroomByCode(command.classroomCode())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Classroom not found")))
                .flatMap(existingClassroom ->
                        classroomRepository.findClassroomByName(command.classroomName())
                                .filter(other -> !other.getCode().equals(existingClassroom.getCode()))
                                .flatMap(conflict -> Mono.error(new IllegalArgumentException("Classroom name already in use")))
                                .then(Mono.defer(() -> {
                                    existingClassroom.setName(command.classroomName());
                                    existingClassroom.setFrName(command.frName());
                                    existingClassroom.setEnName(command.enName());
                                    existingClassroom.setSectionCode(command.sectionCode());
                                    existingClassroom.setSectionName(command.sectionName());
                                    return classroomRepository.save(existingClassroom);
                                }))
                );
    }
}
