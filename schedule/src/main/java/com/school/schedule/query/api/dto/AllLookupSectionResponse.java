package com.school.schedule.query.api.dto;

import com.school.schedule.query.api.response.SectionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = " AllLookup Section response")
public record AllLookupSectionResponse(boolean success, List<SectionResponse> sectionResponses) implements Serializable {}
