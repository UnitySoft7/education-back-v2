package com.system.education.bulletin.detail.query.api.dto;

import com.system.education.bulletin.detail.query.api.response.BulletinDetailResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup bulletin detail response")
public record LookupBulletinDetailResponse(
        boolean success, BulletinDetailResponse bulletinDetailResponse) implements Serializable {}
