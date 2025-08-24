package com.school.presence.dormitory.student.query.api.repository;

import com.school.presence.dormitory.student.core.model.PresenceInDormitory;
import reactor.core.publisher.Mono;

public
interface PresenceInDormitoryRepositories {
    Mono<PresenceInDormitory> getLastPresenceInDormitory ( );
}
