package com.system.education.bulletin.detail.query.api.handler.impl;

import com.system.education.bulletin.detail.query.api.handler.BulletinDetailQueryHandler;
import com.system.education.bulletin.detail.core.model.BulletinDetail;
import com.system.education.bulletin.detail.query.api.query.BulletinDetailByCodeAndSemesterQuery;
import com.system.education.bulletin.detail.query.api.response.PointResponse;
import com.system.education.bulletin.detail.query.api.response.RatingResponse;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByIdQuery;
import com.system.education.bulletin.detail.query.api.repository.BulletinDetailRepository;
import com.system.education.bulletin.detail.query.api.response.BulletinDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BulletinDetailQueryHandlerImpl implements BulletinDetailQueryHandler {
    private final BulletinDetailRepository bulletinDetailRepository;

    /**
     * This method is used to get all bulletin-details
     * @return a flux of bulletin-detail response
     */
    @Override
    public Flux<BulletinDetailResponse> getBulletinDetails() {
        return bulletinDetailRepository.findAll()
                .flatMap(this::getSkillDetailResponse);
    }

    /**
     * This method is used to get a bulletin-detail by ID
     * @param query the ID of the bulletin-detail
     * @return a mono of bulletin-detail response
     */
    @Override
    public Mono<BulletinDetailResponse> getBulletinDetailById(BulletinByIdQuery query) {
        return bulletinDetailRepository.findById(query.id())
                .flatMap(this::getSkillDetailResponse);
    }

    /**
     * This method is used to get a bulletin-detail by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of bulletin-detail response
     */
    @Override
    public Flux<BulletinDetailResponse> getBulletinDetailByEstablishment(BulletinByCodeQuery query) {
        return bulletinDetailRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public Flux<BulletinDetailResponse> getBulletinDetailByEstablishmentAndSemester(BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailRepository.findByEstablishmentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public Flux<BulletinDetailResponse> getBulletinDetailByStudentAndSemester(BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailRepository.findByStudentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public Flux<BulletinDetailResponse> getBulletinDetailByClassAndSemester(BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailRepository.findByClassCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public Flux<BulletinDetailResponse> getBulletinDetailByCourseAndSemester(BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailRepository.findByCourseCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillDetailResponse);
    }

    @Override
    public Mono<PointResponse> getPointByStudentAndSemester(BulletinDetailByCodeAndSemesterQuery query) {
        return bulletinDetailRepository.findByStudentCodeAndSemesterAndSchoolYear(query.code(), query.semester(), query.schoolYear())
                .collectList()
                .flatMap(bulletinDetails -> {
                    if (bulletinDetails.isEmpty()) {
                        return Mono.just(new PointResponse(query.code(), "",
                                "0", "", ""));
                    }
                    double totalAverage = 0;
                    double count = bulletinDetails.size();
                    for (BulletinDetail detail : bulletinDetails) {
                        totalAverage += Double.parseDouble(detail.getAverage());
                    }
                    double average = Math.round(totalAverage / count * 100.0) / 100.0;
                    String grade = getString(average);
                    String[] parts = grade.split("__");
                    return Mono.just(new PointResponse(bulletinDetails.get(0).getStudentCode(), bulletinDetails.get(0).getStudentName(),
                            String.valueOf(totalAverage), parts[0], parts[1]));
                });
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
     * This method is used to convert a bulletin-detail to a bulletin-detail response
     * @param bulletinDetail the bulletin-detail to convert
     * @return the driver response
     */
    private Mono<BulletinDetailResponse> getSkillDetailResponse(BulletinDetail bulletinDetail) {
        return Mono.just(
                new BulletinDetailResponse(
                        bulletinDetail.getBulletinDetailId(), bulletinDetail.getStudentCode(),
                        bulletinDetail.getStudentName(), bulletinDetail.getCourseCode(),
                        bulletinDetail.getCourseName(), bulletinDetail.getMidTermAssessment(),
                        bulletinDetail.getExamination(), bulletinDetail.getAverage(),
                        bulletinDetail.getGrade(), bulletinDetail.getTeacherComment(),
                        bulletinDetail.getTeacherCode(), bulletinDetail.getTeacherName(),
                        bulletinDetail.getSemester(), bulletinDetail.getSchoolYear(),
                        bulletinDetail.getClassCode(), bulletinDetail.getClassName(),
                        bulletinDetail.getEstablishmentCode(), bulletinDetail.getEstablishmentName(),
                        bulletinDetail.getLogCreatedAt(), bulletinDetail.getLogCreatedMonth(),
                        bulletinDetail.getLogCreatedYear(), bulletinDetail.getLogCreatedDate()));
    }
    private String getString(double average) {

        String grade;
        if (average >= 80) {
            grade = "A__EXCELLENT";
        } else if (average >= 70) {
            grade = "B__VERY GOOD";
        } else if (average >= 60) {
            grade = "C__GOOD";
        } else if (average >= 50) {
            grade = "D__AVERAGE";
        } else if (average >= 40) {
            grade = "E__WEAK";
        } else {
            grade = "F__POOR";
        }
        return grade;
    }
}
