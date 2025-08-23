package com.school.minos.core.common;
import reactor.core.publisher.Mono;

public class TransactionCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(3));
            return "TRS" + String.format("%.0f", number + 1);
        });
    }
}
