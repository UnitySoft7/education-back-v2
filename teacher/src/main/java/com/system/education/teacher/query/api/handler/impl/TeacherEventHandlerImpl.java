package com.system.education.teacher.query.api.handler.impl;

import com.system.education.teacher.cmd.api.command.TeacherCreatedCommand;
import com.system.education.teacher.cmd.api.command.TeacherUpdatedCommand;
import com.system.education.teacher.cmd.api.command.TeacherUpdatedFunctionCommand;
import com.system.education.teacher.cmd.api.command.UserAccountCreatedRequest;
import com.system.education.teacher.core.common.LogCreated;
import com.system.education.teacher.core.dto.MessageResponse;
import com.system.education.teacher.core.model.Teacher;
import com.system.education.teacher.core.payload.TeacherPayload;
import com.system.education.teacher.query.api.handler.TeacherEventHandler;
import com.system.education.teacher.query.api.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TeacherEventHandlerImpl implements TeacherEventHandler {
    private final TeacherRepository teacherRepository;
    private final TeacherPayload teacherPayload;

    @Override
    public Mono<Teacher> create(TeacherCreatedCommand command) {
        return teacherPayload.getCode()
                .flatMap(code -> {
                    Teacher teacher = Teacher.builder()
                            .teacherId(UUID.randomUUID().toString())
                            .teacherCode(code)
                            .fullName(command.fullName())
                            .citizenId(command.citizenId())
                            .phoneNo(command.phoneNo())
                            .matricule(command.matricule())
                            .address(command.address())
                            .gender(command.gender())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();
                    UserAccountCreatedRequest request = new UserAccountCreatedRequest(teacher.getTeacherCode(),
                            teacher.getFullName(), command.username(), command.password(), command.verifyPassword(),
                            command.role(), command.establishmentName(), command.establishmentCode());
                    WebClient webClient = WebClient.create();
                    var user = webClient.post()
                            .uri("http://127.0.0.1:9901/api/v1/education/user-account/create-user-account")
                            .bodyValue(request)
                            .retrieve()
                            .bodyToMono(MessageResponse.class);
                    return user.flatMap(userResponse -> {
                        if (userResponse.success()) return teacherRepository.save(teacher);
                        return null;
                    });
                });
    }

    @Override
    public Mono<Teacher> update(TeacherUpdatedCommand command) {
        return teacherRepository.findByTeacherCode(command.teacherCode())
                .flatMap(teacher -> {
                    teacher.setPhoneNo(command.phoneNo());
                    teacher.setAddress(command.address());
                    return teacherRepository.save(teacher);
                });
    }

    @Override
    public Mono<Teacher> updateFunction(TeacherUpdatedFunctionCommand command) {
        return teacherRepository.findByTeacherCode(command.teacherCode())
                .flatMap(teacher -> {
                    teacher.setFunction(command.function());
                    return teacherRepository.save(teacher);
                });
    }
}
