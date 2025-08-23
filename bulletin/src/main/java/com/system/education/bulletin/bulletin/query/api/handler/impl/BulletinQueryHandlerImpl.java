package com.system.education.bulletin.bulletin.query.api.handler.impl;

import com.system.education.bulletin.bulletin.core.model.Bulletin;
import com.system.education.bulletin.bulletin.query.api.handler.BulletinQueryHandler;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeAndSemesterQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByCodeQuery;
import com.system.education.bulletin.bulletin.query.api.query.BulletinByIdQuery;
import com.system.education.bulletin.bulletin.query.api.repository.BulletinRepository;
import com.system.education.bulletin.bulletin.query.api.response.BulletinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BulletinQueryHandlerImpl implements BulletinQueryHandler {
    private final BulletinRepository bulletinRepository;

    /**
     * This method is used to get all bulletins
     * @return a flux of bulletin response
     */
    @Override
    public Flux<BulletinResponse> getBulletins() {
        return bulletinRepository.findAll()
                .flatMap(this::getSkillResponse);
    }

    /**
     * This method is used to get a bulletin by ID
     * @param query the ID of the bulletin
     * @return a mono of bulletin response
     */
    @Override
    public Mono<BulletinResponse> getBulletinById(BulletinByIdQuery query) {
        return bulletinRepository.findById(query.id())
                .flatMap(this::getSkillResponse);
    }

    /**
     * This method is used to get a bulletin by bulletin code
     * @param query the bulletin code of the bulletin
     * @return a mono of bulletin response
     */
    @Override
    public Mono<BulletinResponse> getBulletinByCode(BulletinByCodeQuery query) {
        return bulletinRepository.findByBulletinCode(query.code())
                .flatMap(this::getSkillResponse);
    }

    @Override
    public Flux<BulletinResponse> getBulletinByClass(BulletinByCodeAndSemesterQuery query) {
        return bulletinRepository.findByClassCodeAndSemesterAndSchoolYear(
                query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillResponse);
    }

    @Override
    public Mono<BulletinResponse> getBulletinByStudent(BulletinByCodeAndSemesterQuery query) {
        return bulletinRepository.findByStudentCodeAndSemesterAndSchoolYear(
                        query.code(), query.semester(), query.schoolYear())
                .flatMap(this::getSkillResponse);
    }

    /**
     * This method is used to get a bulletin by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of bulletin response
     */
    @Override
    public Flux<BulletinResponse> getBulletinByEstablishment(BulletinByCodeQuery query) {
        return bulletinRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getSkillResponse);
    }

    /**
     * This method is used to convert a bulletin to a bulletin response
     * @param bulletin the bulletin to convert
     * @return the bulletin response
     */
    private Mono<BulletinResponse> getSkillResponse(Bulletin bulletin) {
        return Mono.just(
                new BulletinResponse(bulletin.getBulletinId(),
                        bulletin.getBulletinCode(), bulletin.getStudentCode(),
                        bulletin.getStudentName(), bulletin.getDateOfBirth(),
                        bulletin.getDaysPresent(), bulletin.getDaysAbsent(),
                        bulletin.getDaysSick(), bulletin.getOtherReason(),
                        bulletin.getPercent(), bulletin.getGrade(),
                        bulletin.getHomeRoomTeacherCode(), bulletin.getHomeRoomTeacherName(),
                        bulletin.getHomeRoomTeacherComment(), bulletin.getAcademicDirectorRemark(),
                        bulletin.getSemester(), bulletin.getSchoolYear(), bulletin.getClassCode(),
                        bulletin.getClassName(), bulletin.getEstablishmentCode(),
                        bulletin.getEstablishmentName(), bulletin.getLogCreatedAt(),
                        bulletin.getLogCreatedMonth(), bulletin.getSchoolYear(),
                        bulletin.getLogCreatedDate())
        );
    }
}
