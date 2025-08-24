package com.school.presence.student.query.api.repository;

import com.school.presence.student.core.model.PresenceInClass;
import reactor.core.publisher.Mono;

public
interface PresenceInClassRepositories {
    Mono<PresenceInClass> getLastPresenceInClass ( );
}
