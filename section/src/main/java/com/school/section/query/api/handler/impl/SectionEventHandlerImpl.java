package com.school.section.query.api.handler.impl;

import com.school.section.cmd.api.command.SectionCreatedCommand;
import com.school.section.cmd.api.command.SectionUpdatedCommand;
import com.school.section.core.model.Section;
import com.school.section.core.payload.SectionPayload;
import com.school.section.query.api.handler.SectionEventHandler;
import com.school.section.query.api.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SectionEventHandlerImpl implements SectionEventHandler {
    private final  SectionRepository sectionRepository;
    private final  SectionPayload sectionPayload;

    @Override
    public Mono<Section> create(SectionCreatedCommand command) {
        return sectionPayload.getCode().flatMap(code -> {
            Section value = Section.builder()
                    .sectionId(UUID.randomUUID().toString())
                    .name(command.sectionName())
                    .sectionCode(code)
                    .frName(command.frName())
                    .enName(command.enName())
                    .establishmentCode(command.establishmentCode())
                    .establishmentName(command.establishmentName())
                    .build();
            return sectionRepository.save(value);
        });
    }

    @Override
    public Mono<Section> update(SectionUpdatedCommand command) {
        return sectionRepository.findSectionBySectionCode(command.sectionCode())
                .flatMap(section -> {
                    section.setName(command.sectionName());
                    section.setFrName(command.frName());
                    section.setEnName(command.enName());
                    return sectionRepository.save(section);
                });
    }

}
