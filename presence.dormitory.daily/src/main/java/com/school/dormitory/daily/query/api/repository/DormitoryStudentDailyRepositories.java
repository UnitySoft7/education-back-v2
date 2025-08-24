package com.school.dormitory.daily.query.api.repository;

import com.school.dormitory.daily.core.model.DormitoryStudentDaily;
import reactor.core.publisher.Mono;

public
interface DormitoryStudentDailyRepositories {
    Mono<DormitoryStudentDaily> getLastDormitoryStudentDaily ( );
}
