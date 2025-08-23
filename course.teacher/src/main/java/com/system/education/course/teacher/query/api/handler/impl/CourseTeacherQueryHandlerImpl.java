package com.system.education.course.teacher.query.api.handler.impl;

import com.system.education.course.teacher.core.model.CourseTeacher;
import com.system.education.course.teacher.query.api.handler.CourseTeacherQueryHandler;
import com.system.education.course.teacher.query.api.query.CourseTeacherByCodeQuery;
import com.system.education.course.teacher.query.api.query.CourseTeacherByIdQuery;
import com.system.education.course.teacher.query.api.repository.CourseTeacherRepository;
import com.system.education.course.teacher.query.api.response.CourseTeacherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseTeacherQueryHandlerImpl implements CourseTeacherQueryHandler {
    private final CourseTeacherRepository courseTeacherRepository;

    /**
     * This method is used to get all course-teachers
     * @return a flux of course-teacher response
     */
    @Override
    public Flux<CourseTeacherResponse> getCourseTeachers() {
        return courseTeacherRepository.findAll()
                .flatMap(this::getCourseTeacherResponse);
    }

    /**
     * This method is used to get a course-teacher by ID
     * @param query the ID of the course-teacher
     * @return a mono of course-teacher response
     */
    @Override
    public Mono<CourseTeacherResponse> getCourseTeacherById(
            CourseTeacherByIdQuery query) {
        return courseTeacherRepository.findById(query.id())
                .flatMap(this::getCourseTeacherResponse);
    }

    /**
     * This method is used to get a course-teacher by course-teacher code
     * @param query the course-teacher code of the course-teacher
     * @return a mono of course-teacher response
     */
    @Override
    public Mono<CourseTeacherResponse> getCourseTeacherByCode(
            CourseTeacherByCodeQuery query) {
        return courseTeacherRepository.findByCourseTeacherCode(query.code())
                .flatMap(this::getCourseTeacherResponse);
    }

    /**
     * This method is used to get a course-teacher by establishment code
     * @param query the establishment code of the course-teacher
     * @return a flux of course-teacher response
     */
    @Override
    public Flux<CourseTeacherResponse> getCourseTeachersByEstablishmentCode(
            CourseTeacherByCodeQuery query) {
        return courseTeacherRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getCourseTeacherResponse);
    }

    /**
     * This method is used to get a course-teacher by establishment, section and class
     * @param query the section code of the course-teacher
     * @return a flux of course-teacher response
     */
    @Override
    public Flux<CourseTeacherResponse> getCourseTeachersByClass(
            CourseTeacherByCodeQuery query) {
        return courseTeacherRepository.findByClassCode(query.code())
                .flatMap(this::getCourseTeacherResponse);
    }

    @Override
    public Flux<CourseTeacherResponse> getCourseTeachersByTeacher(
            CourseTeacherByCodeQuery query) {
        return courseTeacherRepository.findByTeacherCode(query.code())
                .flatMap(this::getCourseTeacherResponse);
    }

    /**
     * This method is used to convert a course-teacher to a course-teacher response
     * @param courseTeacher the course-teacher to convert
     * @return the course-teacher response
     */
    private Mono<CourseTeacherResponse> getCourseTeacherResponse(
            CourseTeacher courseTeacher) {
        return Mono.just(
                new CourseTeacherResponse(courseTeacher.getCourseTeacherId(),
                        courseTeacher.getCourseTeacherCode(), courseTeacher.getEstablishmentCode(),
                        courseTeacher.getEstablishmentName(), courseTeacher.getSectionCode(),
                        courseTeacher.getSectionName(), courseTeacher.getClassCode(), courseTeacher.getClassName(),
                        courseTeacher.getCourseCode(), courseTeacher.getCourseName(),
                        courseTeacher.getTeacherCode(), courseTeacher.getTeacherName(),
                        courseTeacher.getLogCreatedAt(), courseTeacher.getLogCreatedMonth(),
                        courseTeacher.getLogCreatedYear(), courseTeacher.getLogCreatedDate())
        );
    }
}
