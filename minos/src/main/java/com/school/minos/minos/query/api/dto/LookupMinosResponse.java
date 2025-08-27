package com.school.minos.minos.query.api.dto;

import com.school.minos.minos.query.api.response.MinosResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
@Schema(name = "Lookup Minos response")
public record LookupMinosResponse(boolean success, MinosResponse clothResponse) implements Serializable {}
