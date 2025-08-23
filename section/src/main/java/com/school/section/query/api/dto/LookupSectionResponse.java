package com.school.section.query.api.dto;

import com.school.section.query.api.response.SectionResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "Lookup Section response")
public record LookupSectionResponse(boolean success, SectionResponse sectionResponse) implements Serializable {}
