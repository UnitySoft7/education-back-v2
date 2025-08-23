package com.system.education.auth.account.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Employee remote Response")
public record EmployeeRemoteResponse(
        String employeeId,
        String employeeCode,
        String fullName,
        String enterpriseName,
        String enterpriseCode
)implements Serializable{
}
