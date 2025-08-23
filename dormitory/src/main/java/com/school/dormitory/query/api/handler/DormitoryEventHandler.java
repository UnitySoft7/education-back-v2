package com.school.dormitory.query.api.handler;

import com.school.dormitory.cmd.api.command.DormitoryCreatedCommand;
import com.school.dormitory.cmd.api.command.DormitoryUpdatedCommand;
import com.school.dormitory.core.model.Dormitory;
import reactor.core.publisher.Mono;

public
interface DormitoryEventHandler {
    Mono<Dormitory> create ( DormitoryCreatedCommand command );

    Mono<Dormitory> update(DormitoryUpdatedCommand command);
}
