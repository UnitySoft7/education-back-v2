package com.system.education.teacher.core.payload.impl;

import com.system.education.teacher.core.common.TeacherCode;
import com.system.education.teacher.core.dto.MessageResponse;
import com.system.education.teacher.core.payload.TeacherPayload;
import com.system.education.teacher.query.api.repository.TeacherRepositories;
import com.system.education.teacher.query.api.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TeacherPayloadImpl implements TeacherPayload {
    private final TeacherRepository teacherRepository;
    private final TeacherRepositories teacherRepositories;

    /**
     * This method generates a unique code for the teacher.
     * It first checks if there are any existing teachers in the repository.
     * If there are no teachers, it returns a default code "ESTC10000001".
     * If there are existing teachers, it retrieves the last teacher's code and generates a new code based on it.
     *
     * @return A Mono containing the generated teacher code.
     */
    @Override
    public Mono<String> getCode(){
        return teacherRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESTC10000001");
            }
            else {
                Mono<String> code = teacherRepositories.getLastTeacher()
                        .flatMap(teacher -> Mono.just(teacher.getTeacherCode()));
                return TeacherCode.generate(code);
            }
        });
    }

    /**
     * This method checks if a teacher with the given code exists in the repository.
     * It uses the teacherRepository to check for existence.
     *
     * @param teacherCode The teacher code of the teacher to check.
     * @return A Mono containing true if the teacher exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isTeacherCodeExist(String teacherCode) {
        return teacherRepository.existsByTeacherCode(teacherCode);
    }

    @Override
    public Mono<MessageResponse> verifyPassword(String newPassword, String verifyPassword) {
        if (!Objects.equals(newPassword, verifyPassword)){
            return Mono.just(new MessageResponse( false, "Les mots de passe ne correspondent pas"));
        } else {
            return Mono.just(new MessageResponse(true, "OK"));
        }
    }
}
