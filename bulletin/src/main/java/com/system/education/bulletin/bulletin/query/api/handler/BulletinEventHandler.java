package com.system.education.bulletin.bulletin.query.api.handler;

import com.system.education.bulletin.bulletin.cmd.api.command.BulletinCreatedCommand;
import com.system.education.bulletin.bulletin.core.model.Bulletin;
import reactor.core.publisher.Mono;

public interface BulletinEventHandler {
    Mono<Bulletin> create(BulletinCreatedCommand command);
}
