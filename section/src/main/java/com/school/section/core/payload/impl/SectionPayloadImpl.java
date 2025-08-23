package com.school.section.core.payload.impl;
import com.school.section.core.common.SectionCode;
import com.school.section.core.payload.SectionPayload;
import com.school.section.query.api.repository.SectionRepositories;
import com.school.section.query.api.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class SectionPayloadImpl implements SectionPayload {
    private final SectionRepository   sectionRepository;
    private final SectionRepositories sectionRepositories;


    @Override
    public
    Mono<String> getCode ( ){
        return sectionRepository.count().flatMap( aLong -> {
            if (aLong == 0) { return Mono.just("SN100000000001");   }
            else { Mono<String> code = sectionRepositories.getLastSection()  .flatMap(value -> Mono.just(value.getSectionCode())); return SectionCode.generate(code);  }
        });
    }

    @Override
    public
    Mono<Boolean> isSectionNameExist(String sectionName) {
        return sectionRepository.existsSectionByName(sectionName);
    }
    @Override
    public
    Mono<Boolean> isSectionCodeExist ( String SectionCode ) {
        return sectionRepository.existsSectionBySectionCode(SectionCode);
    }

}
