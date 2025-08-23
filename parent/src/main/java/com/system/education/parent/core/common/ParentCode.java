package com.system.education.parent.core.common;

import reactor.core.publisher.Mono;

public class ParentCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(4));
            return "ESPC" + String.format("%.0f", number + 1);
        });
    }
}
