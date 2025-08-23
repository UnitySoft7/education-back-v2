package com.system.education.permission.core.common;

import reactor.core.publisher.Mono;

public class PermissionTypeCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(5));
            return "ESPTC" + String.format("%.0f", number + 1);
        });
    }
}
