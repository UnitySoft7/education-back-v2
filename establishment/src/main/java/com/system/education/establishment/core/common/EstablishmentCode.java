package com.system.education.establishment.core.common;

import reactor.core.publisher.Mono;

public class EstablishmentCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(4));
            return "ESEC" + String.format("%.0f", number + 1);
        });
    }
}
