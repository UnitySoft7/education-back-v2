package com.system.education.bulletin.bulletin.query.api.dto;

import com.system.education.bulletin.bulletin.query.api.response.BulletinResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All lookup bulletin response")
public record AllLookupBulletinResponse(
        boolean success, List<BulletinResponse> bulletinResponses) implements Serializable {}
