package com.system.education.bulletin.detail.core.payload;

import com.system.education.bulletin.detail.cmd.api.command.BulletinDetailCreatedCommand;
import reactor.core.publisher.Mono;

public interface BulletinDetailPayload {
    Mono<Boolean> createBulletinDetailExist(BulletinDetailCreatedCommand command);
}
