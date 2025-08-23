package com.school.evaluation.core.common;
import reactor.core.publisher.Mono;
public class EvaluationCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(3));
            return "EVN" + String.format("%.0f", number + 1);
        });
    }
}
