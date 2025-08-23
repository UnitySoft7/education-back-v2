package com.school.exam.query.api.repository;

import com.school.exam.core.model.Exam;
import reactor.core.publisher.Mono;

public
interface ExamRepositories {
    Mono<Exam> getLastExam ( );
}
