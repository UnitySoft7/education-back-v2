package com.school.course.core.payload.impl;
import com.school.course.core.common.CourseCode;
import com.school.course.core.payload.CoursePayload;
import com.school.course.query.api.repository.CourseRepositories;
import com.school.course.query.api.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@RequiredArgsConstructor
public class CoursePayloadImpl implements CoursePayload {
    private final CourseRepository   courseRepository;
    private final CourseRepositories courseRepositories;


    @Override
    public
    Mono<String> getCode(){
        return courseRepository.count().flatMap( aLong -> {
            if (aLong == 0) {
                return Mono.just("CS100000000001"); }
            else {  Mono<String> code = courseRepositories.getLastCourse()
                        .flatMap(value -> Mono.just(value.getCode ()));
                return CourseCode.generate(code);
            }
        });    }

    @Override
    public
    Mono<Boolean> isClassroomNameExist(String name) {
        return courseRepository.existsCourseByName (name);
    }

    @Override
    public
    Mono<Boolean> isCourseCodeExist(String CourseCode) {
        return courseRepository.existsCourseByCode (CourseCode);
    }

}
