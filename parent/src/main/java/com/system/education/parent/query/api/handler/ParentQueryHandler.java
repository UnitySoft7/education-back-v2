package com.system.education.parent.query.api.handler;

import com.system.education.parent.query.api.query.ParentByCodeQuery;
import com.system.education.parent.query.api.query.ParentByIdQuery;
import com.system.education.parent.query.api.response.ParentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ParentQueryHandler {
    Flux<ParentResponse> getParents();
    Mono<ParentResponse> getParentById(ParentByIdQuery query);
    Mono<ParentResponse> getParentByCode(ParentByCodeQuery query);
}
