package com.system.education.classes.course.query.api.handler.impl;

import com.system.education.classes.course.core.model.ClassCourse;
import com.system.education.classes.course.query.api.handler.ClassCourseQueryHandler;
import com.system.education.classes.course.query.api.query.ClassCourseByCodeQuery;
import com.system.education.classes.course.query.api.query.ClassCourseByIdQuery;
import com.system.education.classes.course.query.api.repository.ClassCourseRepository;
import com.system.education.classes.course.query.api.response.ClassCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClassCourseQueryHandlerImpl implements ClassCourseQueryHandler {
    private final ClassCourseRepository classCourseRepository;

    /**
     * This method is used to get all class-courses
     * @return a flux of class-course response
     */
    @Override
    public Flux<ClassCourseResponse> getClassCourses() {
        return classCourseRepository.findAll()
                .flatMap(this::getClassCourseResponse);
    }

    /**
     * This method is used to get a class-course by ID
     * @param query the ID of the class-course
     * @return a mono of class-course response
     */
    @Override
    public Mono<ClassCourseResponse> getClassCourseById(ClassCourseByIdQuery query) {
        return classCourseRepository.findById(query.id())
                .flatMap(this::getClassCourseResponse);
    }

    /**
     * This method is used to get a class-course by class-course code
     * @param query the class-course code of the class-course
     * @return a mono of class-course response
     */
    @Override
    public Mono<ClassCourseResponse> getClassCourseByCode(ClassCourseByCodeQuery query) {
        return classCourseRepository.findByClassCourseCode(query.code())
                .flatMap(this::getClassCourseResponse);
    }

    /**
     * This method is used to get a class-course by establishment code
     * @param query the establishment code of the class-course
     * @return a flux of class-course response
     */
    @Override
    public Flux<ClassCourseResponse> getClassCoursesByEstablishmentCode(ClassCourseByCodeQuery query) {
        return classCourseRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getClassCourseResponse);
    }

    /**
     * This method is used to get a class-course by class
     * @param query the class code of the class-course
     * @return a flux of class-course response
     */
    @Override
    public Flux<ClassCourseResponse> getClassCoursesByClassCode(ClassCourseByCodeQuery query) {
        return classCourseRepository.findByClassCode(query.code())
                .flatMap(this::getClassCourseResponse);
    }

    /**
     * This method is used to convert a class-course to a class-course response
     * @param classCourse the class-course to convert
     * @return the class-course response
     */
    private Mono<ClassCourseResponse> getClassCourseResponse(ClassCourse classCourse) {
        return Mono.just(new ClassCourseResponse(classCourse.getClassCourseId(),
                classCourse.getClassCourseCode(), classCourse.getEstablishmentCode(),
                classCourse.getEstablishmentName(), classCourse.getSectionCode(),
                classCourse.getSectionName(), classCourse.getClassCode(),
                classCourse.getClassName(), classCourse.getCourseCode(),
                classCourse.getCourseName(), Double.parseDouble(classCourse
                .getPonderation()), classCourse.getLogCreatedAt(),
                classCourse.getLogCreatedMonth(), classCourse.getLogCreatedYear(),
                classCourse.getLogCreatedDate())
        );
    }
}
