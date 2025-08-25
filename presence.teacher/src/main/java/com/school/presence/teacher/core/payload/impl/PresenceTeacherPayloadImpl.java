package com.school.presence.teacher.core.payload.impl;
import com.school.presence.teacher.core.common.LogCreated;
import com.school.presence.teacher.core.common.PresenceTeacherCode;
import com.school.presence.teacher.core.payload.PresenceTeacherPayload;
import com.school.presence.teacher.query.api.repository.PresenceTeacherRepositories;
import com.school.presence.teacher.query.api.repository.PresenceTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class PresenceTeacherPayloadImpl implements PresenceTeacherPayload {
    private final PresenceTeacherRepository   presenceTeacherRepository;
    private final PresenceTeacherRepositories presenceTeacherRepositories;


    @Override
    public
    Mono<String> getCode ( ){
        return presenceTeacherRepository.count().flatMap( aLong -> {
            if (aLong == 0) { return Mono.just("PT100000000001");   }
            else { Mono<String> code = presenceTeacherRepositories.getLastPresenceTeacher()  .flatMap(value -> Mono.just(value.getCode())); return PresenceTeacherCode.generate(code);  }
        });
    }

    @Override
    public Mono<Boolean> exitPresence(String prof) {
        return presenceTeacherRepository.existsByProfAndLogCreatedAt(prof, LogCreated.At());
    }
}
