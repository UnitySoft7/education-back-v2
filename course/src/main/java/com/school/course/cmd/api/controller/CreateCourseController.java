package com.school.course.cmd.api.controller;
import com.school.course.cmd.api.command.CourseCreatedCommand;
import com.school.course.core.dto.MessageResponse;
import com.school.course.core.model.Course;
import com.school.course.core.payload.CoursePayload;
import com.school.course.core.utils.MessageUtilsConstants;
import com.school.course.query.api.handler.CourseEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/course/create-course")
@Tag(name = "Course", description = "Data rest API for Course resource")
public class CreateCourseController {
    private final CourseEventHandler CourseEventHandler;
    private final CoursePayload coursePayload;
    @Operation(summary = "Create Course")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@Valid @RequestBody CourseCreatedCommand command) {
        return coursePayload.isClassroomNameExist(command.courseName())
          .flatMap(nameExists -> {
              if (nameExists) {
                  return Mono.just(ResponseEntity.ok(
                          new MessageResponse(false, MessageUtilsConstants.Name_exist)
                  ));
              } else {
                  Mono<Course> courseMono = CourseEventHandler.create(command);
                  return courseMono.flatMap(create -> {
                      if (create != null) {
                          return Mono.just(ResponseEntity.ok()
                                  .body(new MessageResponse(true, MessageUtilsConstants.created)));
                      }
                      return Mono.just(ResponseEntity.ok()
                              .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                  });
              }
          })
    ;}
}




