package com.system.education.skill.detail.query.api.handler.impl;

import com.system.education.skill.detail.query.api.dto.LookupRatingResponse;
import com.system.education.skill.detail.query.api.handler.SkillDetailQueryHandler;
import com.system.education.skill.detail.core.model.SkillDetail;
import com.system.education.skill.detail.query.api.query.SkillDetailByCodeAndSemesterQuery;
import com.system.education.skill.detail.query.api.response.RatingResponse;
import com.system.education.skill.skill.query.api.query.SkillByCodeQuery;
import com.system.education.skill.skill.query.api.query.SkillByIdQuery;
import com.system.education.skill.detail.query.api.repository.SkillDetailRepository;
import com.system.education.skill.detail.query.api.response.SkillDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SkillDetailQueryHandlerImpl implements SkillDetailQueryHandler {
    private final SkillDetailRepository skillDetailRepository;

    /**
     * This method is used to get all skill-details
     * @return a flux of skill-detail response
     */
    @Override
    public Flux<SkillDetailResponse> getSkillDetails() {
        return skillDetailRepository.findAll()
                .flatMap(this::getSkillDetailResponse);
    }

    /**
     * This method is used to get a skill-detail by ID
     * @param query the ID of the skill-detail
     * @return a mono of skill-detail response
     */
    @Override
    public Mono<SkillDetailResponse> getSkillDetailById(SkillByIdQuery query) {
        return skillDetailRepository.findById(query.id())
                .flatMap(this::getSkillDetailResponse);
    }

    /**
     * This method is used to get a skill-detail by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of skill-detail response
     */
    @Override
    public Flux<SkillDetailResponse> getSkillDetailByEstablishment(SkillByCodeQuery query) {
        return skillDetailRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public Flux<SkillDetailResponse> getSkillDetailByEstablishmentAndSemester(SkillDetailByCodeAndSemesterQuery query) {
        return skillDetailRepository.findByEstablishmentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public Flux<SkillDetailResponse> getSkillDetailByStudentAndSemester(SkillDetailByCodeAndSemesterQuery query) {
        return skillDetailRepository.findByStudentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public Flux<SkillDetailResponse> getSkillDetailBySkillAndSemester(SkillDetailByCodeAndSemesterQuery query) {
        return skillDetailRepository.findBySkillCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public List<RatingResponse> getRating() {
        List<RatingResponse> ratingResponses = new ArrayList<>();
        ratingResponses.add(new RatingResponse("5", "EXCELLENT"));
        ratingResponses.add(new RatingResponse("4", "VERY GOOD"));
        ratingResponses.add(new RatingResponse("3", "GOOD"));
        ratingResponses.add(new RatingResponse("2", "AVERAGE"));
        ratingResponses.add(new RatingResponse("1", "POOR"));
        return ratingResponses;
    }

    /**
     * This method is used to convert a skill-detail to a skill-detail response
     * @param skillDetail the skill-detail to convert
     * @return the driver response
     */
    private Mono<SkillDetailResponse> getSkillDetailResponse(SkillDetail skillDetail) {
        return Mono.just(
                new SkillDetailResponse(skillDetail.getSkillDetailId(),
                        skillDetail.getSkillCode(), skillDetail.getSkillName(),
                        skillDetail.getStudentCode(), skillDetail.getStudentName(),
                        skillDetail.getTeacherComment(), skillDetail.getTeacherCode(),
                        skillDetail.getTeacherName(), skillDetail.getRating(),
                        skillDetail.getSemester(), skillDetail.getSchoolYear(),
                        skillDetail.getEstablishmentCode(), skillDetail.getEstablishmentName(),
                        skillDetail.getLogCreatedAt(), skillDetail.getLogCreatedMonth(),
                        skillDetail.getLogCreatedYear(), skillDetail.getLogCreatedDate()));
    }
}
