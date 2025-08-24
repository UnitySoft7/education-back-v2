package com.school.presence.dormitory.student.query.api.handler;

import com.school.presence.dormitory.student.query.api.response.PresenceInDormitoryResponse;
import com.school.presence.dormitory.student.query.api.response.PresenceQueryResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public
interface PresenceInDormitoryQueryHandler {
    Flux<PresenceInDormitoryResponse> findPresenceInDormitorys();

    Mono<PresenceInDormitoryResponse> findPresenceInDormitoryByPresenceInDormitoryCode(String code);

}