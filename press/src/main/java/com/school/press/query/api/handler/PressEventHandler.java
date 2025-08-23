package com.school.press.query.api.handler;

import com.school.press.cmd.api.command.PressCreatedCommand;
import com.school.press.cmd.api.command.PressUpdatedCommand;
import com.school.press.core.model.Press;
import reactor.core.publisher.Mono;

public
interface PressEventHandler {
    Mono<Press> create ( PressCreatedCommand command );

    Mono<Press> update(PressUpdatedCommand command);
}
