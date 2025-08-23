package com.system.education.bulletin.bulletin.query.api.handler;

import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeAndSemesterQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByIdQuery;
import com.system.education.bulletin.bulletin.query.api.response.BulletinResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BulletinQueryHandler {
    Flux<BulletinResponse> getBulletins();
    Mono<BulletinResponse> getBulletinById(BulletinByIdQuery query);
    Mono<BulletinResponse> getBulletinByCode(BulletinByCodeQuery query);
    Flux<BulletinResponse> getBulletinByClass(BulletinByCodeAndSemesterQuery query);
    Mono<BulletinResponse> getBulletinByStudent(BulletinByCodeAndSemesterQuery query);
    Flux<BulletinResponse> getBulletinByEstablishment(BulletinByCodeQuery query);
}
