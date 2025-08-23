package com.school.evaluate.core.common;
import reactor.core.publisher.Mono;
public class EvaluateCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^EV\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'EV' followed by 12 digits");}
            String numberPart = codeString.substring(2);
            long number = Long.parseLong(numberPart);
            return String.format("EV%012d", number + 1);
        });
    }
}
