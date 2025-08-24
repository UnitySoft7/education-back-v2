package com.school.presence.student.daily.query.api.repository;

import com.school.presence.student.daily.core.model.PresenceStudentDaily;
import reactor.core.publisher.Mono;

public
interface PresenceStudentDailyRepositories {
    Mono<PresenceStudentDaily> getLastPresenceStudentDaily ( );
}
