package com.school.exam.query.api.handler;

import com.school.exam.cmd.api.command.ExamCreatedCommand;
import com.school.exam.cmd.api.command.ExamUpdatedCommand;
import com.school.exam.core.model.Exam;
import reactor.core.publisher.Mono;

public
interface ExamEventHandler {
    Mono<Exam> create ( ExamCreatedCommand command );

    Mono<Exam> update(ExamUpdatedCommand command);
}
