package com.system.education.bulletin.detail.core.payload.impl;

import com.system.education.bulletin.detail.cmd.api.command.BulletinDetailCreatedCommand;
import com.system.education.bulletin.detail.core.payload.BulletinDetailPayload;
import com.system.education.bulletin.detail.query.api.repository.BulletinDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BulletinDetailPayloadImpl implements BulletinDetailPayload {
    private final BulletinDetailRepository bulletinDetailRepository;

    @Override
    public Mono<Boolean> createBulletinDetailExist(BulletinDetailCreatedCommand command) {
        return bulletinDetailRepository.existsByStudentCodeAndCourseCodeAndSemesterAndSchoolYear
                (command.studentCode(), command.courseCode(), command.semester(), command.schoolYear());
    }
}
