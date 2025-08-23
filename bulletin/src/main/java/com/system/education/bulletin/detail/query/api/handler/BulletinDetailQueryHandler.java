package com.system.education.bulletin.detail.query.api.handler;

import com.system.education.bulletin.detail.query.api.query.BulletinDetailByCodeAndSemesterQuery;
import com.system.education.bulletin.detail.query.api.response.PointResponse;
import com.system.education.bulletin.detail.query.api.response.RatingResponse;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByIdQuery;
import com.system.education.bulletin.detail.query.api.response.BulletinDetailResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BulletinDetailQueryHandler {
    Flux<BulletinDetailResponse> getBulletinDetails();
    Mono<BulletinDetailResponse> getBulletinDetailById(BulletinByIdQuery query);
    Flux<BulletinDetailResponse> getBulletinDetailByEstablishment(BulletinByCodeQuery query);
    Flux<BulletinDetailResponse> getBulletinDetailByEstablishmentAndSemester(BulletinDetailByCodeAndSemesterQuery query);
    Flux<BulletinDetailResponse> getBulletinDetailByStudentAndSemester(BulletinDetailByCodeAndSemesterQuery query);
    Flux<BulletinDetailResponse> getBulletinDetailByClassAndSemester(BulletinDetailByCodeAndSemesterQuery query);

    Flux<BulletinDetailResponse> getBulletinDetailByCourseAndSemester(BulletinDetailByCodeAndSemesterQuery query);

    Mono<PointResponse> getPointByStudentAndSemester(BulletinDetailByCodeAndSemesterQuery query);

    List<RatingResponse> getRating();
}
