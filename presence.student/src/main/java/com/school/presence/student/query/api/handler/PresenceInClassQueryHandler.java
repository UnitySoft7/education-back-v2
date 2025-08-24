package com.school.presence.student.query.api.handler;

import com.school.presence.student.query.api.response.PresenceInClassResponse;
import com.school.presence.student.query.api.response.PresenceQueryResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface PresenceInClassQueryHandler {
    Flux<PresenceInClassResponse> findPresenceInClasss();

    Mono<PresenceInClassResponse> findPresenceInClassByPresenceInClassCode(String code);

}