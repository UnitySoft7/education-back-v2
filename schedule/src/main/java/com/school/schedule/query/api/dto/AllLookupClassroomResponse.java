package com.school.schedule.query.api.dto;
import com.school.schedule.query.api.response.ClassroomResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Classroom Response")
public record AllLookupClassroomResponse(boolean success, List<ClassroomResponse> classroomResponses) implements Serializable {}
