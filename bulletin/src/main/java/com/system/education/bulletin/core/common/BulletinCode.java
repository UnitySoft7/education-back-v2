package com.system.education.bulletin.core.common;

import reactor.core.publisher.Mono;

public class BulletinCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> {
            double number = Double.parseDouble(codeString.substring(5));
            return "ESBTC" + String.format("%.0f", number + 1);
        });
    }
}
