package com.system.education.bulletin.detail.query.api.handler;

import com.system.education.bulletin.detail.cmd.api.command.BulletinDetailCreatedCommand;
import com.system.education.bulletin.detail.cmd.api.command.BulletinDetailUpdatedCommand;
import com.system.education.bulletin.detail.core.model.BulletinDetail;
import reactor.core.publisher.Mono;

public interface BulletinDetailEventHandler {
    Mono<BulletinDetail> create(BulletinDetailCreatedCommand command);

    Mono<BulletinDetail> update(BulletinDetailUpdatedCommand command);
}
