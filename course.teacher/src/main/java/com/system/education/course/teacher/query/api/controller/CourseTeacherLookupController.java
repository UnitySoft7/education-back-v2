package com.system.education.course.teacher.query.api.controller;

import com.system.education.course.teacher.query.api.dto.AllLookupCourseTeacherResponse;
import com.system.education.course.teacher.query.api.dto.LookupCourseTeacherResponse;
import com.system.education.course.teacher.query.api.handler.CourseTeacherQueryHandler;
import com.system.education.course.teacher.query.api.query.CourseTeacherByCodeQuery;
import com.system.education.course.teacher.query.api.query.CourseTeacherByIdQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/course-teacher/course-teacher-lookup/")
@Tag(name = "Course-Teacher", description = "Data REST API for course-teacher resource")
public class CourseTeacherLookupController {
    private final CourseTeacherQueryHandler courseTeacherQueryHandler;

    /**
     * This method is used to retrieve all courses-teacher
     * @return the list of courses-teacher
     */
    @Operation(summary = "Retrieve data courses-teacher")
    @GetMapping(path = "get-courses-teacher", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupCourseTeacherResponse>> show() {
        return courseTeacherQueryHandler.getCourseTeachers()
                .collectList()
                .map(establishmentSectionResponses ->
                        new AllLookupCourseTeacherResponse(true, establishmentSectionResponses))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a course-teacher by ID
     * @param query the ID of the course-teacher
     * @return the course-teacher with the specified ID
     */
    @Operation(summary = "Retrieve data course-teacher by ID")
    @PutMapping(path = "get-course-teacher-by-course-teacher-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody CourseTeacherByIdQuery query) {
        return courseTeacherQueryHandler.getCourseTeacherById(query)
                .map(establishmentSectionResponse ->
                        new LookupCourseTeacherResponse(true, establishmentSectionResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a course-teacher by course-teacher code
     * @param query the code of the course-teacher
     * @return the course-teacher with the specified code
     */
    @Operation(summary = "Retrieve data course-teacher by code")
    @PutMapping(path = "get-course-teacher-by-course-teacher-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishmentSectionCode(@Valid @RequestBody CourseTeacherByCodeQuery query) {
        return courseTeacherQueryHandler.getCourseTeacherByCode(query)
                .map(establishmentSectionResponse ->
                        new LookupCourseTeacherResponse(true, establishmentSectionResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a course-teacher by establishment code
     * @param query the code of the course-teacher
     * @return the list of course-teacher with the establishment code
     */
    @Operation(summary = "Retrieve data course-teacher by establishment code")
    @PutMapping(path = "get-course-teacher-by-establishment-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishmentCode(@Valid @RequestBody CourseTeacherByCodeQuery query) {
        return courseTeacherQueryHandler.getCourseTeachersByEstablishmentCode(query)
                .collectList()
                .map(establishmentSectionResponse ->
                        new AllLookupCourseTeacherResponse(true, establishmentSectionResponse))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data course-teacher by teacher code")
    @PutMapping(path = "get-course-teacher-by-teacher-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByTeacherCode(@Valid @RequestBody CourseTeacherByCodeQuery query) {
        return courseTeacherQueryHandler.getCourseTeachersByTeacher(query)
                .collectList()
                .map(establishmentSectionResponse ->
                        new AllLookupCourseTeacherResponse(true, establishmentSectionResponse))
                .map(ResponseEntity::ok);
    }
}
