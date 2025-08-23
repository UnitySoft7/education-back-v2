package com.system.education.infirmary.core.common;

import reactor.core.publisher.Mono;

public class ProductCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(5));
            return "ESIPC" + String.format("%.0f", number + 1);
        });
    }
}
