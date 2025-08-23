package com.system.education.parent.query.api.handler;

import com.system.education.parent.cmd.api.command.ParentCreatedCommand;
import com.system.education.parent.cmd.api.command.ParentUpdatedCommand;
import com.system.education.parent.core.model.Parent;
import reactor.core.publisher.Mono;

public interface ParentEventHandler {
    Mono<Parent> create(ParentCreatedCommand command);

    Mono<Parent> update(ParentUpdatedCommand command);
}
