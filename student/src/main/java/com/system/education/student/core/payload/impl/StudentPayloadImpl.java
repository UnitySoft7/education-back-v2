package com.system.education.student.core.payload.impl;

import com.system.education.student.core.common.StudentCode;
import com.system.education.student.core.payload.StudentPayload;
import com.system.education.student.query.api.repository.StudentRepositories;
import com.system.education.student.query.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentPayloadImpl implements StudentPayload {
    private final StudentRepository studentRepository;
    private final StudentRepositories studentRepositories;

    /**
     * This method generates a unique code for the student.
     * It first checks if there are any existing students in the repository.
     * If there are no students, it returns a default code "ESPC10000001".
     * If there are existing students, it retrieves the last student's code and generates a new code based on it.
     *
     * @return A Mono containing the generated student code.
     */
    @Override
    public Mono<String> getCode(){
        return studentRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESSTC10000001");
            }
            else {
                Mono<String> code = studentRepositories.getLastStudent()
                        .flatMap(student -> Mono.just(student.getStudentCode()));
                return StudentCode.generate(code);
            }
        });
    }

    /**
     * This method checks if an student with the given code exists in the repository.
     * It uses the studentRepository to check for existence.
     *
     * @param studentCode The student code of the student to check.
     * @return A Mono containing true if the student exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isStudentCodeExist(String studentCode) {
        return studentRepository.existsByStudentCode(studentCode);
    }
}
