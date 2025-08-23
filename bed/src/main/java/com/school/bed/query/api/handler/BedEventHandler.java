package com.school.bed.query.api.handler;

import com.school.bed.cmd.api.command.BedCreatedCommand;
import com.school.bed.cmd.api.command.BedUpdatedCommand;
import com.school.bed.core.model.Bed;
import reactor.core.publisher.Mono;

public
interface BedEventHandler {
    Mono<Bed> create ( BedCreatedCommand command );

    Mono<Bed> update(BedUpdatedCommand command);
}
