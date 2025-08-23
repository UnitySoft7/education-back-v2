package com.system.education.student.core.common;

import reactor.core.publisher.Mono;

public class StudentCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(5));
            return "ESSTC" + String.format("%.0f", number + 1);
        });
    }
}
