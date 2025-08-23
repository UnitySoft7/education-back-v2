package com.school.course.query.api.controller;

import com.school.course.query.api.dto.AllLookupCourseResponse;
import com.school.course.query.api.dto.LookupCourseResponse;
import com.school.course.query.api.handler.CourseQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/course/lookup-course/")
@Tag(name = "Course", description = "Data REST API for class resource")
public class CourseLookupController {
    private final CourseQueryHandler CourseQueryHandler;
    @Operation(summary = "Retrieve data Course")
    @GetMapping(path = "get-courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupCourseResponse>> show() {
        return CourseQueryHandler.findCourses()
                .collectList()
                .map(Course ->
                        new AllLookupCourseResponse (true, Course))
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Retrieve data Course by courseCode")
    @GetMapping(path = "get-course-by-course-code/{courseCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupCourseResponse>> getCourseByCourseCode(@PathVariable("courseCode") String courseCode ) {
        return CourseQueryHandler.findCourseByCourseCode (courseCode)
                .map(Classroom ->
                        new LookupCourseResponse (true, Classroom))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupCourseResponse(false, null))));
    }

    @Operation(summary = "Retrieve data course by establishmentCode")
    @GetMapping(path = "get-course-by-establishment/{establishmentCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupCourseResponse>>findCourseByEstablishment(
            @PathVariable("establishmentCode") String establishmentCode ) {
        return CourseQueryHandler.findCourseByEstablishment (establishmentCode)
                .collectList()
                .map(Classroom ->
                        new AllLookupCourseResponse (true, Classroom))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new AllLookupCourseResponse(false, List.of()))));
    }
}
