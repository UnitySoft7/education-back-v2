package com.school.section.query.api.handler;

import com.school.section.cmd.api.command.SectionCreatedCommand;
import com.school.section.cmd.api.command.SectionUpdatedCommand;
import com.school.section.core.model.Section;
import reactor.core.publisher.Mono;

public
interface SectionEventHandler {
    Mono<Section> create ( SectionCreatedCommand command );

    Mono<Section> update(SectionUpdatedCommand command);
}
