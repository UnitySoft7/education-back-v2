package com.school.classroom.query.api.dto;

import com.school.classroom.query.api.response.ClassroomResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "Lookup Classroom response")
public record LookupClassroomResponse(boolean success, ClassroomResponse classroomResponse) implements Serializable {}
