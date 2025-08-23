package com.school.press.core.dto;

import java.io.Serializable;
import java.util.Map;

public record FieldsValidatorResponse(boolean success, Map<String, String> fieldsValidator) implements Serializable {}