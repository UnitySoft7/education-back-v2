package com.system.education.skill.detail.query.api.dto;

import com.system.education.skill.detail.query.api.response.RatingResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "Lookup rating response")
public record LookupRatingResponse(
        boolean success, List<RatingResponse> ratingResponses) implements Serializable {}
