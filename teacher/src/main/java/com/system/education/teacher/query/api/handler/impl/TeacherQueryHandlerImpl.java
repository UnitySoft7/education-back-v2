package com.system.education.teacher.query.api.handler.impl;

import com.system.education.teacher.core.model.Teacher;
import com.system.education.teacher.query.api.handler.TeacherQueryHandler;
import com.system.education.teacher.query.api.query.TeacherByCodeQuery;
import com.system.education.teacher.query.api.query.TeacherByIdQuery;
import com.system.education.teacher.query.api.repository.TeacherRepository;
import com.system.education.teacher.query.api.response.NameResponse;
import com.system.education.teacher.query.api.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherQueryHandlerImpl implements TeacherQueryHandler {
    private final TeacherRepository teacherRepository;

    /**
     * This method is used to get all teachers
     * @return a flux of teacher response
     */
    @Override
    public Flux<TeacherResponse> getTeachers() {
        return teacherRepository.findAll()
                .flatMap(this::getParentResponse);
    }

    /**
     * This method is used to get a teacher by ID
     * @param query the ID of the teacher
     * @return a mono of teacher response
     */
    @Override
    public Mono<TeacherResponse> getTeacherById(TeacherByIdQuery query) {
        return teacherRepository.findById(query.id())
                .flatMap(this::getParentResponse);
    }

    /**
     * This method is used to get a teacher by teacher code
     * @param query the teacher code of the teacher
     * @return a mono of teacher response
     */
    @Override
    public Mono<TeacherResponse> getTeacherByCode(TeacherByCodeQuery query) {
        return teacherRepository.findByTeacherCode(query.code())
                .flatMap(this::getParentResponse);
    }

    /**
     * This method is used to get a teacher by establishment code
     * @param query the establishment code of the teacher
     * @return a mono of establishment response
     */
    @Override
    public Flux<TeacherResponse> getTeacherByEstablishmentCode(TeacherByCodeQuery query) {
        return teacherRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getParentResponse);
    }

    /**
     * This method is used to convert a teacher to a teacher response
     * @param teacher the teacher to convert
     * @return the driver response
     */
    private Mono<TeacherResponse> getParentResponse(Teacher teacher) {
        return Mono.just(
                new TeacherResponse(teacher.getTeacherId(),
                        teacher.getTeacherCode(), teacher.getFullName(),
                        teacher.getCitizenId(), teacher.getPhoneNo(),
                        teacher.getMatricule(), teacher.getAddress(),
                        teacher.getGender(), teacher.getEstablishmentCode(),
                        teacher.getEstablishmentName(), teacher.getLogCreatedAt(),
                        teacher.getLogCreatedMonth(), teacher.getLogCreatedYear(),
                        teacher.getLogCreatedDate())
        );
    }

    @Override
    public List<NameResponse> getFunction() {
        return List.of(
                new NameResponse("Administration", "Administration"),
                new NameResponse("Teacher", "Teacher"),
                new NameResponse("Infirmary", "Infirmary"),
                new NameResponse("Cafeteria", "Cafeteria"));
    }

    @Override
    public Flux<TeacherResponse> getTeacherByTeacher() {
        return teacherRepository.findByFunction("Teacher")
                .flatMap(this::getParentResponse);
    }

    @Override
    public Flux<TeacherResponse> getTeacherByInfirmary() {
        return teacherRepository.findByFunction("Infirmary")
                .flatMap(this::getParentResponse);
    }

    @Override
    public Flux<TeacherResponse> getTeacherByCafeteria() {
        return teacherRepository.findByFunction("Cafeteria")
                .flatMap(this::getParentResponse);
    }

    @Override
    public Flux<TeacherResponse> getTeacherByAdministration() {
        return teacherRepository.findByFunction("Administration")
                .flatMap(this::getParentResponse);
    }
}
