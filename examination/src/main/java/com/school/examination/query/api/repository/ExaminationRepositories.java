package com.school.examination.query.api.repository;

import com.school.examination.core.model.Examination;
import reactor.core.publisher.Mono;

public
interface ExaminationRepositories {
    Mono<Examination> getLastExamination ( );
}
