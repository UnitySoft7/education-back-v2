package com.school.dormitory.core.payload;


import reactor.core.publisher.Mono;

public
interface DormitoryPayload {
    Mono<String> getCode();

    Mono<Boolean> isDormitoryNameExist(String name);

    Mono<Boolean> isDormitoryCodeExist(String DormitoryCode);
}