package com.system.education.student.query.api.handler.impl;

import com.system.education.student.core.model.Student;
import com.system.education.student.query.api.handler.StudentQueryHandler;
import com.system.education.student.query.api.query.StudentByCodeQuery;
import com.system.education.student.query.api.query.StudentByIdQuery;
import com.system.education.student.query.api.repository.StudentRepository;
import com.system.education.student.query.api.response.PresenceClassResponse;
import com.system.education.student.query.api.response.StudentClassResponse;
import com.system.education.student.query.api.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentQueryHandlerImpl implements StudentQueryHandler {
    private final StudentRepository studentRepository;

    /**
     * This method is used to get all students
     * @return a flux of student response
     */
    @Override
    public Flux<StudentResponse> getStudents() {
        return studentRepository.findAll()
                .flatMap(this::getStudentResponse);
    }

    /**
     * This method is used to get a student by ID
     * @param query the ID of the student
     * @return a mono of student response
     */
    @Override
    public Mono<StudentResponse> getStudentById(StudentByIdQuery query) {
        return studentRepository.findById(query.id())
                .flatMap(this::getStudentResponse);
    }

    /**
     * This method is used to get a student by student code
     * @param query the student code of the student
     * @return a mono of student response
     */
    @Override
    public Mono<StudentResponse> getStudentByCode(StudentByCodeQuery query) {
        return studentRepository.findByStudentCode(query.code())
                .flatMap(this::getStudentResponse);
    }

    /**
     * This method is used to get a student by parent code
     * @param query the parent code of the parent
     * @return a mono of student response
     */
    @Override
    public Flux<StudentResponse> getStudentByParent(StudentByCodeQuery query) {
        return studentRepository.findByParentCode(query.code())
                .flatMap(this::getStudentResponse);
    }

    /**
     * This method is used to get a student by class code
     * @param query the class code of the class
     * @return a mono of student response
     */
    @Override
    public Flux<StudentResponse> getStudentByClass(StudentByCodeQuery query) {
        return studentRepository.findByClassCode(query.code())
                .flatMap(this::getStudentResponse);
    }

    /**
     * This method is used to get a student by section code
     * @param query the section code of the section
     * @return a mono of student response
     */
    @Override
    public Flux<StudentResponse> getStudentBySection(StudentByCodeQuery query) {
        return studentRepository.findBySectionCode(query.code())
                .flatMap(this::getStudentResponse);
    }

    /**
     * This method is used to get a student by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of student response
     */
    @Override
    public Flux<StudentResponse> getStudentByEstablishment(StudentByCodeQuery query) {
        return studentRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getStudentResponse);
    }



    @Override
    public Mono<UrlResource> getStudentProfileByCode(StudentByCodeQuery query) {
        return studentRepository.findByStudentCode(query.code())
                .flatMap(student -> {
                    Path filePath = Path.of(student.getProfilePath());
                    try {
                        return Mono.just(new UrlResource(filePath.toUri()));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }



    @Override
    public Mono<UrlResource> getStudentExtractByCode(StudentByCodeQuery query) {
        return studentRepository.findByStudentCode(query.code())
                .flatMap(student -> {
                    Path filePath = Path.of(student.getExtractPath());
                    try {
                        return Mono.just(new UrlResource(filePath.toUri()));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public Mono<UrlResource> getStudentBulletinByCode(StudentByCodeQuery query) {
        return studentRepository.findByStudentCode(query.code())
                .flatMap(student -> {
                    Path filePath = Path.of(student.getBulletinPath());
                    try {
                        return Mono.just(new UrlResource(filePath.toUri()));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * This method is used to convert a student to a student response
     * @param student the student to convert
     * @return the driver response
     */
    private Mono<StudentResponse> getStudentResponse(Student student) {
        return Mono.just(
                new StudentResponse(student.getStudentId(),
                        student.getStudentCode(), student.getFullName(),
                        student.getBirthday(), student.getGender(),
                        student.getAddress(), student.getFatherName(),
                        student.getFatherMobileNo(), student.getMotherName(),
                        student.getMotherMobileNo(), student.getParentCode(),
                        student.getParentName(), student.getSchoolYear(),
                        student.getEstablishmentCode(), student.getEstablishmentName(),
                        student.getSectionCode(), student.getSectionName(),
                        student.getClassCode(), student.getClassName(),
                        student.getLogCreatedAt(), student.getLogCreatedMonth(),
                        student.getLogCreatedYear(), student.getLogCreatedDate())
        );
    }

    @Override
    public Mono<PresenceClassResponse> getPresenceStudentByClass(StudentByCodeQuery query) {
        return studentRepository.findByClassCode(query.code())
                .collectList()
                .map(this::getPresenceClassResponse);
    }

    private PresenceClassResponse getPresenceClassResponse(List<Student> students) {
        List<StudentClassResponse> studentClassResponses = new ArrayList<>();
        for (Student student : students) {
            studentClassResponses.add(new StudentClassResponse(
                    student.getStudentCode(), student.getFullName()));
        }
        return new PresenceClassResponse(studentClassResponses, String.valueOf(studentClassResponses.size()));
    }
}
