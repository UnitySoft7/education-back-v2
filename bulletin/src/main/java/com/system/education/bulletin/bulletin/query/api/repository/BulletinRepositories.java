package com.system.education.bulletin.bulletin.query.api.repository;

import com.system.education.bulletin.bulletin.core.model.Bulletin;
import reactor.core.publisher.Mono;

public interface BulletinRepositories {
    Mono<Bulletin> getLastBulletin();
}
