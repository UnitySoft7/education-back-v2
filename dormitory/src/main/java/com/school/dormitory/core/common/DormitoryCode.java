package com.school.dormitory.core.common;
import reactor.core.publisher.Mono;
public class DormitoryCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            if (codeString == null || !codeString.matches("^DM\\d{12}$")) {
                throw new IllegalArgumentException("Code must match format 'DM' followed by 12 digits");}
            String numberPart = codeString.substring(2);
            long number = Long.parseLong(numberPart);
            return String.format("DM%012d", number + 1);
        });
    }
}






