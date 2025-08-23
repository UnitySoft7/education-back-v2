package com.system.education.establishment.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Establishment response")
public record EstablishmentResponse(
        String establishmentId, String establishmentCode, String establishmentName,
        String nickName, String nif, String rc, String phoneNo,
        String email, String site, String state, String province, String commune,
        String zone, String quartier, String locality, String logCreatedAt,
        String logCreatedMonth, String logCreatedYear, String logCreatedDate,
        String logModifiedDate) implements Serializable {}
