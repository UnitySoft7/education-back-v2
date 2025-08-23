package com.system.education.course.teacher.core.payload.impl;

import com.system.education.course.teacher.core.common.CourseTeacherCode;
import com.system.education.course.teacher.core.payload.CourseTeacherPayload;
import com.system.education.course.teacher.query.api.dto.LookupEstablishmentSectionClassCourseResponse;
import com.system.education.course.teacher.query.api.dto.LookupTeacherResponse;
import com.system.education.course.teacher.query.api.query.CourseTeacherByCodeQuery;
import com.system.education.course.teacher.query.api.repository.CourseTeacherRepositories;
import com.system.education.course.teacher.query.api.repository.CourseTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseTeacherPayloadImpl implements CourseTeacherPayload {
    private final CourseTeacherRepository courseTeacherRepository;
    private final CourseTeacherRepositories courseTeacherRepositories;

    /**
     * This method generates a unique code for the course-teacher.
     * It first checks if there are any existing course-teacher in the repository.
     * If there are no course-teacher, it returns a default code "ESCTC10000001".
     * If there are existing course-teacher, it retrieves the last course-teacher's code and generates a new code based on it.
     *
     * @return A Mono containing the generated course-teacher code.
     */
    @Override
    public Mono<String> getCode(){
        return courseTeacherRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESCTC10000001");
            }
            else {
                Mono<String> code = courseTeacherRepositories.getLastEstablishmentSectionClassCourse()
                        .flatMap(courseTeacherCode -> Mono.just(courseTeacherCode.getCourseTeacherCode()));
                return CourseTeacherCode.generate(code);
            }
        });
    }

    /**
     * This method checks if a course-teacher with the given code exists in the repository.
     * It uses the courseRepository-teacher to check for existence.
     *
     * @param establishmentSectionClassCourseCode The course-teacher code of the course-teacher to check.
     * @return A Mono containing true if the course-teacher exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isEstablishmentSectionCodeClassCourseExist(String establishmentSectionClassCourseCode) {
        return courseTeacherRepository.existsByCourseTeacherCode(establishmentSectionClassCourseCode);
    }



    @Override
    public Mono<LookupTeacherResponse> verifyTeacherCode(String code) {
        CourseTeacherByCodeQuery query = new CourseTeacherByCodeQuery(code);
        return WebClient.create()
                .put()
                .uri("https://teacher-j53m.onrender.com/api/v1/education/teacher/teacher-lookup/get-teacher-by-teacher-code")
//                .uri("http://127.0.0.1:9904/api/v1/education/teacher/teacher-lookup/get-teacher-by-teacher-code")
                .bodyValue(query)
                .retrieve()
                .bodyToMono(LookupTeacherResponse.class);
    }

    @Override
    public Mono<LookupEstablishmentSectionClassCourseResponse> verifyEstablishmentSectionClassCourseCode(String code) {
        CourseTeacherByCodeQuery query = new CourseTeacherByCodeQuery(code);
        return WebClient.create()
                .put()
                .uri("https://course-teacher-41np.onrender.com/api/v1/education/course-teacher/course-teacher-lookup/get-course-teacher-by-course-teacher-code")
//                .uri("http://127.0.0.1:9908/api/v1/education/course-teacher/course-teacher-lookup/get-course-teacher-by-course-teacher-code")
                .bodyValue(query)
                .retrieve()
                .bodyToMono(LookupEstablishmentSectionClassCourseResponse.class);
    }
}
