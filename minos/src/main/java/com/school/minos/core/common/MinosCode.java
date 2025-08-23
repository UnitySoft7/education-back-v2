package com.school.minos.core.common;
import reactor.core.publisher.Mono;
public class MinosCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(2));
            return "ML" + String.format("%.0f", number + 1);
        });
    }
}
