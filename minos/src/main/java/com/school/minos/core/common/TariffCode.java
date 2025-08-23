package com.school.minos.core.common;
import reactor.core.publisher.Mono;

public class TariffCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(4));
            return "ESTM" + String.format("%.0f", number + 1);
        });
    }
}
