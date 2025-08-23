package com.school.evaluate.query.api.handler.impl;
import com.school.evaluate.cmd.api.command.EvaluateCreatedCommand;
import com.school.evaluate.cmd.api.command.EvaluateReclaimedCommand;
import com.school.evaluate.cmd.api.command.EvaluateUpdatedCommand;
import com.school.evaluate.core.model.Evaluate;
import com.school.evaluate.core.payload.EvaluatePayload;
import com.school.evaluate.query.api.handler.EvaluateEventHandler;
import com.school.evaluate.query.api.repository.EvaluateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class EvaluateEventHandlerImpl implements EvaluateEventHandler {
    private final  EvaluateRepository evaluateRepository;
    private final  EvaluatePayload  evaluatePayload;
    @Override
    public
    Mono<Evaluate> create ( EvaluateCreatedCommand command ) {
       return evaluatePayload.getCode().flatMap(code -> {
                        Evaluate value = Evaluate.builder()
                                .evaluateId (UUID.randomUUID().toString())
                                .code(code)
                                .profFullname ( command.profFullname() )
                                .profCode(command.profCode())
                                .courseName(command.courseName())
                                .courseCode(command.courseCode())
                                .courseCode(command.courseCode())
                                .evaluationCode(command.evaluationCode())
                                .evaluationName(command.evaluationName())
                                .studentCode ( command.studentCode () )
                                .studentFullname ( command.studentFullname () )
                                .noteMax (Double.parseDouble(command.noteMax ()))
                                .note(Double.parseDouble(command.note()))
//                                .ESCC( command.ESCC () )
//                                .ESCS(command.ESCCT())
//                                .ESCCT(command.ESCCT())
                                .establishmentCode ( command.establishmentCode () )
                                .establishmentName ( command.establishmentName ( ) )
                                .sectionName ( command.sectionName () )
                                .sectionCode ( command.sectionCode () )
                                .classroomName ( command.classroomName () )
                                .classroomCode ( command.classroomCode () )
                                .trimester(command.semester())
                                .schoolYear(command.schoolYear())
                                .comment(command.comment())
                                .build();
                        return evaluateRepository.save(value);
                    });
                }

    @Override
    public Mono<Evaluate> update(EvaluateUpdatedCommand command) {
        return evaluateRepository.findEvaluateByCode (command.evaluateCode())
                .flatMap(evaluate -> {
                    evaluate.setNote(command.note());
                    evaluate.setSchoolYear(command.schoolYear());
                    evaluate.setTrimester(command.semester());
                    return evaluateRepository.save(evaluate);
                });
    }

    @Override
    public Mono<Evaluate> reclaim(EvaluateReclaimedCommand command) {
        return evaluateRepository.findEvaluateByCode (command.evaluateCode())
                .flatMap(evaluate -> {
                    evaluate.setNote(Double.parseDouble(command.note()));
                    return evaluateRepository.save(evaluate);
                });
    }

}
