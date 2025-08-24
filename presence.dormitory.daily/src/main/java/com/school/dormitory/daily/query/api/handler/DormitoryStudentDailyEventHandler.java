package com.school.dormitory.daily.query.api.handler;

import com.school.dormitory.daily.cmd.api.command.DormitoryStudentDailyCreatedCommand;
import com.school.dormitory.daily.core.model.DormitoryStudentDaily;
import reactor.core.publisher.Mono;

public
interface DormitoryStudentDailyEventHandler {
    Mono<DormitoryStudentDaily> create ( DormitoryStudentDailyCreatedCommand command );

}
