package com.school.exam.query.api.handler.impl;

import com.school.exam.cmd.api.command.ExamCreatedCommand;
import com.school.exam.cmd.api.command.ExamUpdatedCommand;
import com.school.exam.core.common.LogCreated;
import com.school.exam.core.model.Exam;
import com.school.exam.core.payload.ExamPayload;
import com.school.exam.query.api.handler.ExamEventHandler;
import com.school.exam.query.api.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ExamEventHandlerImpl implements ExamEventHandler {
    private final  ExamRepository examRepository;
    private final  ExamPayload examPayload;
    @Override
    public  Mono<Exam> create ( ExamCreatedCommand command ) {
       return examPayload.getCode().flatMap(code -> {
                        Exam value = Exam.builder()
                                .examId (UUID.randomUUID().toString())
                                .code(code)
                                .name ( command.name () )
                                .profFullname ( command.profFullname () )
                                .profCode ( command.profCode () )
                                .noteMax (Double.parseDouble(command.noteMax()))
                                .establishmentCode ( command.establishmentCode () )
                                .establishmentName ( command.establishmentName ( ) )
                                .sectionName ( command.sectionName () )
                                .sectionCode ( command.sectionCode () )
                                .classroomName ( command.classroomName () )
                                .classroomCode ( command.classroomCode () )
                                .courseCode(command.courseCode())
                                .courseName(command.courseName())
                                .trimester(command.trimester())
                                .schoolYear(command.schoolYear())
                                .logCreatedAt(LogCreated.At())
                                .logCreatedDate(LogCreated.Date())
                                .logCreatedMonth(LogCreated.Month())
                                .logCreatedYear(LogCreated.Year())

                                .build();
                        return examRepository.save(value);
                    });
    }
    @Override
    public Mono<Exam> update(ExamUpdatedCommand command) {
        return examRepository.findExamByCode(command.examCode())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Exam not found")))
                .flatMap(exam -> {
                    exam.setNoteMax(Double.parseDouble(command.noteMax()));
                    return examRepository.save(exam);
                });
    }

}
