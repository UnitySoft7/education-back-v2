package com.school.presence.teacher.query.api.repository;

import com.school.presence.teacher.core.model.PresenceTeacher;
import reactor.core.publisher.Mono;

public
interface PresenceTeacherRepositories {
    Mono<PresenceTeacher> getLastPresenceTeacher ( );
}
