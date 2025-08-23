package com.school.dormitory.student.bed.press.query.api.repository;

import com.school.dormitory.student.bed.press.core.model.DormitoryStudentBedPress;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DormitoryStudentBedPressRepository extends ReactiveMongoRepository<DormitoryStudentBedPress, String> {
    Mono<Boolean> existsDormitoryStudentBedPressByCode ( @Param("code") String code);
    Mono<DormitoryStudentBedPress> findDormitoryStudentBedPressByCode ( @Param("code") String code);
}
