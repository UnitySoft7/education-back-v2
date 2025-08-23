package com.system.education.classes.course.query.api.controller;

import com.system.education.classes.course.query.api.dto.AllLookupClassCourseResponse;
import com.system.education.classes.course.query.api.dto.LookupClassCourseResponse;
import com.system.education.classes.course.query.api.handler.ClassCourseQueryHandler;
import com.system.education.classes.course.query.api.query.ClassCourseByCodeQuery;
import com.system.education.classes.course.query.api.query.ClassCourseByIdQuery;
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
@RequestMapping(path = "/api/v1/education/class-course/class-course-lookup/")
@Tag(name = "Class-Course", description = "Data REST API for class-course resource")
public class ClassCourseLookupController {
    private final ClassCourseQueryHandler classCourseQueryHandler;

    /**
     * This method is used to retrieve all class-courses
     * @return the list of class-courses
     */
    @Operation(summary = "Retrieve data class-courses")
    @GetMapping(path = "get-class-courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupClassCourseResponse>> show() {
        return classCourseQueryHandler.getClassCourses()
                .collectList()
                .map(establishmentSectionResponses ->
                        new AllLookupClassCourseResponse(true, establishmentSectionResponses))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a class-course by ID
     * @param query the ID of the class-course
     * @return the class-course with the specified ID
     */
    @Operation(summary = "Retrieve data class-course by ID")
    @PutMapping(path = "get-class-course-by-class-course-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody ClassCourseByIdQuery query) {
        return classCourseQueryHandler.getClassCourseById(query)
                .map(establishmentSectionResponse ->
                        new LookupClassCourseResponse(true, establishmentSectionResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a class-course by class-course code
     * @param query the code of the class-course
     * @return the class-course with the specified code
     */
    @Operation(summary = "Retrieve data class-course by code")
    @PutMapping(path = "get-class-course-by-class-course-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupClassCourseResponse>> getByEstablishmentSectionCode(@Valid @RequestBody ClassCourseByCodeQuery query) {
        return classCourseQueryHandler.getClassCourseByCode(query)
                .map(establishmentSectionResponse ->
                        new LookupClassCourseResponse(true, establishmentSectionResponse))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupClassCourseResponse(false, null))));
    }

    /**
     * This method is used to retrieve a class-course by establishment code
     * @param query the code of the class-course
     * @return the list of class-course with the establishment code
     */
    @Operation(summary = "Retrieve data class-course by establishment code")
    @PutMapping(path = "get-class-course-by-establishment-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishmentCode(@Valid @RequestBody ClassCourseByCodeQuery query) {
        return classCourseQueryHandler.getClassCoursesByEstablishmentCode(query)
                .collectList()
                .map(establishmentSectionResponse ->
                        new AllLookupClassCourseResponse(true, establishmentSectionResponse))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a class-course by class code
     * @param query the code of the class-course
     * @return the list of class-course with class code
     */
    @Operation(summary = "Retrieve data class-course by class code")
    @PutMapping(path = "get-class-course-by-class-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByClassCode(@Valid @RequestBody ClassCourseByCodeQuery query) {
        return classCourseQueryHandler.getClassCoursesByClassCode(query)
                .collectList()
                .map(establishmentSectionResponse ->
                        new AllLookupClassCourseResponse(true, establishmentSectionResponse))
                .map(ResponseEntity::ok);
    }
}
