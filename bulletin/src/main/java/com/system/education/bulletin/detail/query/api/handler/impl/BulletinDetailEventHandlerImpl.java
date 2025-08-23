package com.system.education.bulletin.detail.query.api.handler.impl;

import com.system.education.bulletin.core.common.LogCreated;
import com.system.education.bulletin.detail.query.api.handler.BulletinDetailEventHandler;
import com.system.education.bulletin.detail.cmd.api.command.BulletinDetailCreatedCommand;
import com.system.education.bulletin.detail.cmd.api.command.BulletinDetailUpdatedCommand;
import com.system.education.bulletin.detail.core.model.BulletinDetail;
import com.system.education.bulletin.detail.query.api.repository.BulletinDetailRepository;
import com.system.education.bulletin.bulletin.query.api.repository.BulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BulletinDetailEventHandlerImpl implements BulletinDetailEventHandler {
    private final BulletinDetailRepository bulletinDetailRepository;
    private final BulletinRepository bulletinRepository;

    @Override
    public Mono<BulletinDetail> create(BulletinDetailCreatedCommand command) {

        double average = command.midTermAssessment() != null && command.examination() != null
                ? Math.round((Double.parseDouble(command.midTermAssessment()) + Double.parseDouble(command.examination())) / 2 * 100.0) / 100.0
                : 0;
        String grade = getString(average);

        BulletinDetail bulletinDetail = BulletinDetail.builder()
                .bulletinDetailId(UUID.randomUUID().toString())
                .studentCode(command.studentCode())
                .studentName(command.studentName())
                .courseCode(command.courseCode())
                .courseName(command.courseName())
                .midTermAssessment(command.midTermAssessment())
                .examination(command.examination())
                .average("" + average)
                .grade(grade)
                .teacherComment(command.teacherComment())
                .teacherCode(command.teacherCode())
                .teacherName(command.teacherName())
                .semester(command.semester())
                .schoolYear(command.schoolYear())
                .classCode(command.classCode())
                .className(command.className())
                .establishmentCode(command.establishmentCode())
                .establishmentName(command.establishmentName())
                .logCreatedAt(LogCreated.At())
                .logCreatedMonth(LogCreated.Month())
                .logCreatedYear(LogCreated.Year())
                .logCreatedDate(LogCreated.Date())
                .build();
        return bulletinDetailRepository.save(bulletinDetail);
    }

    private String getString(double average) {
        String grade;

//                    if (average >= 80) {
//                        grade = "A";
//                    } else if (average >= 70) {
//                        grade = "B";
//                    } else if (average >= 60) {
//                        grade = "C";
//                    } else if (average >= 50) {
//                        grade = "D";
//                    } else if (average >= 40) {
//                        grade = "E";
//                    } else {
//                        grade = "F";
//                    }

        if (average >= 80) {
            grade = "5";
        } else if (average >= 70) {
            grade = "4";
        } else if (average >= 60) {
            grade = "3";
        } else if (average >= 50) {
            grade = "2";
        } else {
            grade = "1";
        }
        return grade;
    }

    @Override
    public Mono<BulletinDetail> update(BulletinDetailUpdatedCommand command) {
         return bulletinRepository.findById(command.bulletinDetailId())
                .flatMap(bulletin -> bulletinDetailRepository.findById(command.bulletinDetailId())
                .flatMap(permission -> {
                    permission.setTeacherComment(command.teacherComment());
                    permission.setTeacherCode(command.teacherCode());
                    permission.setTeacherName(command.teacherName());
                    return bulletinDetailRepository.save(permission);
                }));
    }
}
