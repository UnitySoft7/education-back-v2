package com.school.classroom.core.payload.impl;
import com.school.classroom.core.common.ClassroomCode;
import com.school.classroom.core.payload.ClassroomPayload;
import com.school.classroom.query.api.repository.ClassroomRepositories;
import com.school.classroom.query.api.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class ClassroomPayloadImpl implements ClassroomPayload {
    private final ClassroomRepository   classroomRepository;
    private final ClassroomRepositories classroomRepositories;
    @Override
    public
    Mono<String> getCode ( ){
        return classroomRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("CR100000000001");
            }
            else {
                Mono<String> code = classroomRepositories.getLastClassroom()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return ClassroomCode.generate(code);
            }
        });
    }

    @Override
    public
    Mono<Boolean> isClassroomNameExist(String name) {
        return classroomRepository.existsClassroomByName (name);
    }

    @Override
    public
    Mono<Boolean> isClassroomCodeExist ( String ClassroomCode ) {
        return classroomRepository.existsClassroomByCode (ClassroomCode);
    }

}
