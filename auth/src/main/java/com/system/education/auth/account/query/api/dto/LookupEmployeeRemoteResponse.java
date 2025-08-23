package com.system.education.auth.account.query.api.dto;

import com.system.education.auth.account.query.api.response.EmployeeRemoteResponse;

import java.io.Serializable;

public record LookupEmployeeRemoteResponse(boolean success, EmployeeRemoteResponse response) implements Serializable {
}
