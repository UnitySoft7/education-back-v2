package com.school.press.core.common;
import reactor.core.publisher.Mono;
public class PressCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^PS\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'PS' followed by 12 digits");}
            String numberPart = codeString.substring(2);
            long number = Long.parseLong(numberPart);
            return String.format("PS%012d", number + 1);
        });
    }
}






