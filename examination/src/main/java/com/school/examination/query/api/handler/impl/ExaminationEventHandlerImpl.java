package com.school.examination.query.api.handler.impl;

import com.school.examination.cmd.api.command.ExaminationCreatedCommand;
import com.school.examination.cmd.api.command.ExaminationReclaimedCommand;
import com.school.examination.core.model.Examination;
import com.school.examination.core.payload.ExaminationPayload;
import com.school.examination.query.api.handler.ExaminationEventHandler;
import com.school.examination.query.api.repository.ExaminationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ExaminationEventHandlerImpl implements ExaminationEventHandler {
    private final  ExaminationRepository examinationRepository;
    private final  ExaminationPayload examinationPayload;
    @Override
    public
    Mono<Examination> create (ExaminationCreatedCommand command ) {
       return examinationPayload.getCode().flatMap(code -> {
                        Examination value = Examination.builder()
                                .examinationId (UUID.randomUUID().toString())
                                .code(code)
                                .examName(command.examName()).examCode(command.examCode())
                                .studentCode ( command.studentCode () )
                                .studentFullname ( command.studentFullname () )
                                .noteMax (Double.parseDouble(command.noteMax ()))
                                .note(Double.parseDouble(command.note()))
                                .comment(command.comment())
                                .establishmentCode ( command.establishmentCode () )
                                .establishmentName ( command.establishmentName ( ) )
                                .sectionName ( command.sectionName () )
                                .sectionCode ( command.sectionCode () )
                                .classroomName ( command.classroomName () )
                                .classroomCode ( command.classroomCode () ).profCode(command.profCode()).profFullname(command.profFullname())
                                .courseCode(command.courseCode()).courseName(command.courseName())
                                .trimester (command.semester () )
                                .schoolYear (command.schoolYear () )
                                .build();
                        return examinationRepository.save(value);
                    });
                }



    @Override
    public Mono<Examination> reclaim(ExaminationReclaimedCommand command) {
        return examinationRepository.findExaminationByCode (command.examinationCode())
                .flatMap(examination -> {
                    examination.setNote (Double.parseDouble(command.note()));
                    return examinationRepository.save(examination);
                });
    }

}
