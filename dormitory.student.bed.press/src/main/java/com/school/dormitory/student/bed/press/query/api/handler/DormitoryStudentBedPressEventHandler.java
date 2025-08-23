package com.school.dormitory.student.bed.press.query.api.handler;

import com.school.dormitory.student.bed.press.cmd.api.command.DormitoryStudentBedPressCreatedCommand;
import com.school.dormitory.student.bed.press.cmd.api.command.DormitoryStudentBedPressUpdatedCommand;
import com.school.dormitory.student.bed.press.core.model.DormitoryStudentBedPress;
import reactor.core.publisher.Mono;

public
interface DormitoryStudentBedPressEventHandler {
    Mono<DormitoryStudentBedPress> create ( DormitoryStudentBedPressCreatedCommand command );

    Mono<DormitoryStudentBedPress> update(DormitoryStudentBedPressUpdatedCommand command);

//    Mono<DormitoryStudentBedPress> update(DormitoryStudentBedPressUpdatedCommand command);
}
