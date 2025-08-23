package com.system.education.bulletin.bulletin.query.api.dto;

import com.system.education.bulletin.bulletin.query.api.response.BulletinResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Lookup bulletin response")
public record LookupBulletinResponse(
        boolean success, BulletinResponse bulletinResponse) implements Serializable {}
