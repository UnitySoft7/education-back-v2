package com.school.examination.query.api.repository;
import com.school.examination.core.model.Examination;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface ExaminationRepository extends ReactiveMongoRepository<Examination, String> {
    Mono<Boolean> existsExaminationByCode(@Param("code") String code);
    Mono<Examination> findExaminationByCode(@Param("code") String code);
    Flux<Examination> findAllExaminationByTrimester(@Param("trimester") String trimester);
    Flux<Examination> findAllExaminationBySchoolYear(@Param("year") String year);
    Mono<Examination> findExaminationByStudentCodeAndCourseCodeAndTrimesterAndSchoolYear(
            @Param("student") String student,
            @Param("courseCode") String courseCode,
            @Param("semester") String semester,
            @Param("year") String year
    );
    Flux<Examination> findExaminationByEstablishmentCodeAndClassroomCodeAndSectionCodeAndProfCodeAndCourseCodeAndTrimesterAndSchoolYear
            (@Param("establishment") String establishment,
             @Param("classroom") String classroom,
             @Param("section") String section,
             @Param("prof") String prof,
             @Param("course") String course,
             @Param("semester") String semester,
             @Param("year") String year);
    Mono<Boolean> existsByExamCodeAndStudentCode(@Param("examCode") String examCode, @Param("studentCode") String studentCode);
}