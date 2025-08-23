package com.system.education.skill.detail.query.api.handler;

import com.system.education.skill.detail.query.api.dto.LookupRatingResponse;
import com.system.education.skill.detail.query.api.query.SkillDetailByCodeAndSemesterQuery;
import com.system.education.skill.detail.query.api.response.RatingResponse;
import com.system.education.skill.skill.query.api.query.SkillByCodeQuery;
import com.system.education.skill.skill.query.api.query.SkillByIdQuery;
import com.system.education.skill.detail.query.api.response.SkillDetailResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SkillDetailQueryHandler {
    Flux<SkillDetailResponse> getSkillDetails();
    Mono<SkillDetailResponse> getSkillDetailById(SkillByIdQuery query);
    Flux<SkillDetailResponse> getSkillDetailByEstablishment(SkillByCodeQuery query);
    Flux<SkillDetailResponse> getSkillDetailByEstablishmentAndSemester(SkillDetailByCodeAndSemesterQuery query);
    Flux<SkillDetailResponse> getSkillDetailByStudentAndSemester(SkillDetailByCodeAndSemesterQuery query);
    Flux<SkillDetailResponse> getSkillDetailBySkillAndSemester(SkillDetailByCodeAndSemesterQuery query);
    List<RatingResponse> getRating();
}
