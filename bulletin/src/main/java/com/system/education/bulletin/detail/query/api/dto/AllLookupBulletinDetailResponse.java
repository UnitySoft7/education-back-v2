package com.system.education.bulletin.detail.query.api.dto;

import com.system.education.bulletin.detail.query.api.response.BulletinDetailResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup bulletin detail response")
public record AllLookupBulletinDetailResponse(
        boolean success, List<BulletinDetailResponse> bulletinDetailResponses) implements Serializable {}
