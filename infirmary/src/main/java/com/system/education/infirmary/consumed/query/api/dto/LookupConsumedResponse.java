package com.system.education.infirmary.consumed.query.api.dto;

import com.system.education.infirmary.consumed.query.api.response.ConsumedResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup consumed response")
public record LookupConsumedResponse(
        boolean success, ConsumedResponse consumedResponse) implements Serializable {}
