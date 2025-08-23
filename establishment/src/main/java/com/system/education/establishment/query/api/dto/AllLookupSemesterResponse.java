package com.system.education.establishment.query.api.dto;

import com.system.education.establishment.query.api.response.SemesterResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Semester Response")
public record AllLookupSemesterResponse(
        boolean success, List<SemesterResponse> semesterResponses) implements Serializable {}
