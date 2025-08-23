package com.school.bed.query.api.dto;

import com.school.bed.query.api.response.BedResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Bed Response")
public record AllLookupBedResponse(boolean success, List<BedResponse> BedResponses) implements Serializable {}
