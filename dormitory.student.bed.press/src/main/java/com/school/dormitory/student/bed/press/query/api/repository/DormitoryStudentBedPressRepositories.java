package com.school.dormitory.student.bed.press.query.api.repository;

import com.school.dormitory.student.bed.press.core.model.DormitoryStudentBedPress;
import reactor.core.publisher.Mono;

public
interface DormitoryStudentBedPressRepositories {
    Mono<DormitoryStudentBedPress> getLastDormitoryStudentBedPress ( );
}
