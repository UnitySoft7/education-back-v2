package com.school.dormitory.daily.query.api.handler.impl;

import com.school.dormitory.daily.cmd.api.command.DormitoryStudentDailyCreatedCommand;
import com.school.dormitory.daily.core.common.LogCreated;
import com.school.dormitory.daily.core.model.DormitoryStudentDaily;
import com.school.dormitory.daily.core.payload.DormitoryStudentDailyPayload;
import com.school.dormitory.daily.query.api.handler.DormitoryStudentDailyEventHandler;
import com.school.dormitory.daily.query.api.repository.DormitoryStudentDailyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DormitoryStudentDailyEventHandlerImpl implements DormitoryStudentDailyEventHandler {
    private final  DormitoryStudentDailyRepository dormitoryStudentDailyRepository;
    private final  DormitoryStudentDailyPayload  dormitoryStudentDailyPayload;
    @Override
    public
    Mono<DormitoryStudentDaily> create ( DormitoryStudentDailyCreatedCommand command ) {
       return dormitoryStudentDailyPayload.getCode().flatMap(code -> {
                        DormitoryStudentDaily value = DormitoryStudentDaily.builder()
                                .dormitoryStudentDailyId(UUID.randomUUID().toString())
                                .code(code)
                                .dormitoryInClassCode(command.dormitoryInClassCode())
                                .dormitory(command.dormitory())
                                .student(command.student())
                                .presentStatus(command.status())
                                .prof(command.prof())
                                .scheduleCode(command.scheduleCode())
                                .absents(command.absents())
                                .presents(command.presents())
                                .schoolYear(command.schoolYear())
                                .trimester(command.trimester())
                                .status(command.status())
                                .logCreatedAt(LogCreated.At())
                                .build();
                        return dormitoryStudentDailyRepository.save(value);
                    });
                }



}
