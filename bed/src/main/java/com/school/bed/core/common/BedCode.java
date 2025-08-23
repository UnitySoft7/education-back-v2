package com.school.bed.core.common;
import reactor.core.publisher.Mono;
public class BedCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^BD\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'BD' followed by 12 digits");}
            String numberPart = codeString.substring(2);
            long number = Long.parseLong(numberPart);
            return String.format("BD%012d", number + 1);
        });
    }
}






