package com.system.education.classes.course.core.payload.impl;

import com.system.education.classes.course.cmd.api.command.ClassCourseCreatedCommand;
import com.system.education.classes.course.cmd.api.command.ClassCourseUpdatedCommand;
import com.system.education.classes.course.core.common.ClassCourseCode;
import com.system.education.classes.course.core.payload.ClassCoursePayload;
import com.system.education.classes.course.query.api.dto.LookupCourseResponse;
import com.system.education.classes.course.query.api.dto.LookupEstablishmentSectionClassResponse;
import com.system.education.classes.course.query.api.query.ClassCourseByCodeQuery;
import com.system.education.classes.course.query.api.repository.ClassCourseRepositories;
import com.system.education.classes.course.query.api.repository.ClassCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClassCoursePayloadImpl implements ClassCoursePayload {
    private final ClassCourseRepository classCourseRepository;
    private final ClassCourseRepositories classCourseRepositories;

    /**
     * This method generates a unique code for the class-course.
     * It first checks if there are any existing class-course in the repository.
     * If there are no class-course, it returns a default code "ESCC10000001".
     * If there are existing class-course, it retrieves the last class-course's code and generates a new code based on it.
     *
     * @return A Mono containing the generated class-course code.
     */
    @Override
    public Mono<String> getCode(){
        return classCourseRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESCC10000001");
            }
            else {
                Mono<String> code = classCourseRepositories.getLastClassCourse()
                        .flatMap(establishmentSectionClass -> Mono.just(establishmentSectionClass.getClassCourseCode()));
                return ClassCourseCode.generate(code);
            }
        });
    }

    /**
     * This method checks if a class-course with the given code exists in the repository.
     * It uses the class-courseRepository to check for existence.
     *
     * @param classCourseCode The class-course code of the class-course to check.
     * @return A Mono containing true if the class-course exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isClassCourseExist(String classCourseCode) {
        return classCourseRepository.existsByClassCourseCode(classCourseCode);
    }

    @Override
    public Mono<Boolean> createException(ClassCourseCreatedCommand command) {
        return classCourseRepository.existsByClassCodeAndCourseCode(command.classCode(), command.courseCode());
    }

    @Override
    public Mono<Boolean> updateException(ClassCourseUpdatedCommand command) {

        return classCourseRepository.findByClassCourseCode(command.classCourseCode())
                .flatMap(classCourse -> classCourseRepository
                        .existsByClassCodeAndCourseCode(classCourse.getClassCode(), command.courseCode())
                        .flatMap(exists -> {
                            if (!exists) {
                                return Mono.just(false);
                            }
                            if (classCourse.getCourseCode().equals(command.courseCode())) {
                                return Mono.just(false);
                            } else {
                                return classCourseRepository.existsByClassCodeAndCourseCode(
                                        classCourse.getClassCode(), command.courseCode());
                            }
                        }));
    }



    @Override
    public Mono<LookupCourseResponse> verifyCourseCode(String code) {
        return WebClient.create()
                .get()
                .uri("https://course-4tm1.onrender.com/api/v1/education/course/lookup-course/get-course-by-course-code/{courseCode}", code)
//                .uri("http://127.0.0.1:9803/api/v1/education/course/lookup-course/get-course-by-course-code/{courseCode}", code)
                .retrieve()
                .bodyToMono(LookupCourseResponse.class);
    }

    @Override
    public Mono<LookupEstablishmentSectionClassResponse> verifyEstablishmentSectionClassCode(String code) {
        ClassCourseByCodeQuery query = new ClassCourseByCodeQuery(code);
        return WebClient.create()
                .put()
                .uri("https://class-s2sm.onrender.com/api/v1/education/class/class-lookup/get-class-by-class-code")
//                .uri("http://127.0.0.1:9907/api/v1/education/class/class-lookup/get-class-by-class-code")
                .bodyValue(query)
                .retrieve()
                .bodyToMono(LookupEstablishmentSectionClassResponse.class);
    }
}
