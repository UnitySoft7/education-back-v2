package com.school.section.query.api.dto;

import com.school.section.query.api.response.SectionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Section Response")
public record AllLookupSectionResponse(boolean success, List<SectionResponse> sectionResponses) implements Serializable {}
