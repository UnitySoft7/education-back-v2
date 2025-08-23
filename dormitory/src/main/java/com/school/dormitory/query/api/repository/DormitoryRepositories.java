package com.school.dormitory.query.api.repository;

import com.school.dormitory.core.model.Dormitory;
import reactor.core.publisher.Mono;

public
interface DormitoryRepositories {
    Mono<Dormitory> getLastDormitory ( );
}
