package com.system.education.classes.course.query.api.dto;

import com.system.education.classes.course.query.api.response.ClassCourseResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup class course response")
public record LookupClassCourseResponse(
        boolean success, ClassCourseResponse classCourseResponse) implements Serializable {}
