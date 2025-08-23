package com.system.education.student.query.api.handler.impl;

import com.system.education.student.cmd.api.command.AddParentCommand;
import com.system.education.student.cmd.api.command.StudentCreatedCommand;
import com.system.education.student.cmd.api.command.StudentUpdatedCommand;
import com.system.education.student.core.common.LogCreated;
import com.system.education.student.core.model.Student;
import com.system.education.student.core.payload.StudentPayload;
import com.system.education.student.query.api.handler.StudentEventHandler;
import com.system.education.student.query.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentEventHandlerImpl implements StudentEventHandler {
    private final StudentRepository studentRepository;
    private final StudentPayload studentPayload;
    String uploadDir = "student/src/main/resources/static/uploads";
    private final Path rootImage = Paths.get(uploadDir);

    @Override
    public Mono<Student> create(StudentCreatedCommand command) {
        return studentPayload.getCode()
                .flatMap(code -> {

                    if (!Files.exists(rootImage)) {
                        try {
                            Files.createDirectories(rootImage);
                        } catch (IOException e) {
                            return Mono.error(new RuntimeException("Erreur création dossier"));
                        }
                    }

                    String profileName = UUID.randomUUID() + "_" + command.profile().filename();
                    String extractName = UUID.randomUUID() + "_" + command.extract().filename();
                    String bulletinName = UUID.randomUUID() + "_" + command.bulletin().filename();

                    Path destinationProfile = rootImage.resolve(profileName).normalize().toAbsolutePath();
                    Path destinationExtract = rootImage.resolve(extractName).normalize().toAbsolutePath();
                    Path destinationBulletin = rootImage.resolve(bulletinName).normalize().toAbsolutePath();

                    Mono<Void> saveProfile = command.profile().transferTo(destinationProfile);
                    Mono<Void> saveExtract = command.extract().transferTo(destinationExtract);
                    Mono<Void> saveBulletin = command.bulletin().transferTo(destinationBulletin);

                    Student student = Student.builder()
                            .studentId(UUID.randomUUID().toString())
                            .studentCode(code)
                            .fullName(command.fullName())
                            .birthday(command.birthday())
                            .gender(command.gender())
                            .address(command.address())
                            .fatherName(command.fatherName())
                            .fatherMobileNo(command.fatherMobileNo())
                            .motherName(command.motherName())
                            .motherMobileNo(command.motherMobileNo())
                            .profilePath(destinationProfile.toString())
                            .extractPath(destinationExtract.toString())
                            .bulletinPath(destinationBulletin.toString())
                            .schoolYear(command.schoolYear())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .sectionCode(command.sectionCode())
                            .sectionName(command.sectionName())
                            .classCode(command.classCode())
                            .className(command.className())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();
                    return Mono.when(saveProfile, saveExtract, saveBulletin)
                            .then(studentRepository.save(student));
                });
    }

    @Override
    public Mono<Student> update(StudentUpdatedCommand command) {
        return studentRepository.findByStudentCode(command.studentCode())
                .flatMap(student -> {
                    student.setGender(command.gender());
                    student.setAddress(command.address());
                    student.setSchoolYear(command.schoolYear());
                    student.setSectionCode(command.sectionCode());
                    student.setSectionName(command.sectionName());
                    student.setClassCode(command.classCode());
                    student.setClassName(command.className());
                    return studentRepository.save(student);
                });
    }

    @Override
    public Mono<Student> addParent(AddParentCommand command) {
        return studentRepository.findByStudentCode(command.studentCode())
                .flatMap(student -> {
                    student.setParentCode(command.parentCode());
                    student.setParentName(command.parentName());
                    return studentRepository.save(student);
                });
    }
}
