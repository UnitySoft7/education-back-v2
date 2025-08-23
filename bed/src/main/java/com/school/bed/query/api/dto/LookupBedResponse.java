package com.school.bed.query.api.dto;
import com.school.bed.query.api.response.BedResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
@Schema(name = "Lookup Bed response")
public record LookupBedResponse(boolean success, BedResponse clothResponse) implements Serializable {}
