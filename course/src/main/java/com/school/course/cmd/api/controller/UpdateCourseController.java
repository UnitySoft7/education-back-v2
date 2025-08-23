package com.school.course.cmd.api.controller;

import com.school.course.cmd.api.command.CourseUpdatedCommand;
import com.school.course.core.dto.MessageResponse;
import com.school.course.core.payload.CoursePayload;
import com.school.course.core.utils.MessageUtilsConstants;
import com.school.course.query.api.handler.CourseEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/course/update-course")
@Tag(name = "Course", description = "Data rest API for updadte resource")
public class UpdateCourseController {
    private final CourseEventHandler updadteEventHandler;
    private final CoursePayload updadtePayload;
    /**
     * This method is used to update the updadte state to available
     * @param command the command containing the updadte information
     * @return the message for operation
     */
    @Operation(summary = "updadte")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create (@RequestBody CourseUpdatedCommand command) {
        return updadtePayload.isCourseCodeExist(command.courseCode ())
                .flatMap(exist -> {
                    if (exist) {
                        Mono<com.school.course.core.model.Course> updadteMono = updadteEventHandler.update (command);
                        return updadteMono.flatMap(updadte -> {
                            if (updadte != null) {
                                return Mono.just(ResponseEntity.ok()
                                        .body(new MessageResponse(true, MessageUtilsConstants.updated)));
                            }
                            return Mono.just(ResponseEntity.ok()
                                    .body(new MessageResponse(false, MessageUtilsConstants.operation_failed)));
                        });
                    }
                    return Mono.just(ResponseEntity.ok()
                            .body(new MessageResponse(false, MessageUtilsConstants.code_not_found)));
                });
    }
}
