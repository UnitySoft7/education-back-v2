package com.system.education.skill.detail.query.api.handler.impl;

import com.system.education.skill.core.common.LogCreated;
import com.system.education.skill.detail.query.api.handler.SkillDetailEventHandler;
import com.system.education.skill.detail.cmd.api.command.SkillDetailCreatedCommand;
import com.system.education.skill.detail.cmd.api.command.SkillDetailUpdatedCommand;
import com.system.education.skill.detail.core.model.SkillDetail;
import com.system.education.skill.detail.query.api.repository.SkillDetailRepository;
import com.system.education.skill.skill.query.api.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SkillDetailEventHandlerImpl implements SkillDetailEventHandler {
    private final SkillDetailRepository skillDetailRepository;
    private final SkillRepository skillRepository;

    @Override
    public Mono<SkillDetail> create(SkillDetailCreatedCommand command) {
        return skillRepository.findBySkillCode(command.skillCode())
                .flatMap(skill -> {
                    SkillDetail skillDetail = SkillDetail.builder()
                            .skillDetailId(UUID.randomUUID().toString())
                            .skillCode(command.skillCode())
                            .skillName(skill.getSkillName())
                            .studentCode(command.studentCode())
                            .studentName(command.studentName())
                            .teacherComment(command.teacherComment())
                            .teacherCode(command.teacherCode())
                            .teacherName(command.teacherName())
                            .rating(command.rating())
                            .semester(command.semester())
                            .schoolYear(command.schoolYear())
                            .establishmentCode(command.establishmentCode())
                            .establishmentName(command.establishmentName())
                            .logCreatedAt(LogCreated.At())
                            .logCreatedMonth(LogCreated.Month())
                            .logCreatedYear(LogCreated.Year())
                            .logCreatedDate(LogCreated.Date())
                            .build();
                    return skillDetailRepository.save(skillDetail);
                });
    }

    @Override
    public Mono<SkillDetail> update(SkillDetailUpdatedCommand command) {
         return skillRepository.findBySkillCode(command.skillCode())
                .flatMap(type -> skillDetailRepository.findById(command.skillDetailId())
                .flatMap(permission -> {
                    permission.setSkillCode(command.skillCode());
                    permission.setSkillName(type.getSkillName());
                    permission.setTeacherComment(command.teacherComment());
                    permission.setStudentCode(command.studentCode());
                    permission.setStudentName(command.studentName());
                    permission.setTeacherCode(command.teacherCode());
                    permission.setTeacherName(command.teacherName());
                    permission.setRating(command.rating());
                    return skillDetailRepository.save(permission);
                }));
    }
}
