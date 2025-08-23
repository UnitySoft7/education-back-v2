package com.school.exam.query.api.handler.impl;

import com.school.exam.cmd.api.query.FindByCodeAndYearQuery;
import com.school.exam.cmd.api.query.FindByStudentTrimesterYearQuery;
import com.school.exam.cmd.api.query.FindByTrimesterQuery;
import com.school.exam.cmd.api.query.FindByYearQuery;
import com.school.exam.core.model.Exam;
import com.school.exam.query.api.handler.ExamQueryHandler;
import com.school.exam.query.api.repository.ExamRepository;
import com.school.exam.query.api.response.ExamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExamQueryHandlerImpl implements ExamQueryHandler {
    private final ExamRepository examRepository;

    @Override
    public Flux<ExamResponse> findExams() {
        return examRepository.findAll().map(this::getExam);
    }

    @Override
    public Mono<ExamResponse> findExamByExamCode(String clothCode) {
        return examRepository.findExamByCode(clothCode).map(this::getExam);
    }
    @Override
    public Flux<ExamResponse> findAllExamByTrimester(FindByTrimesterQuery query) {
        return examRepository.findExamByTrimester(query.trimester()).map(this::getExam);
    }

    @Override
    public Flux<ExamResponse> findAllExamByYear(FindByYearQuery query) {
        return examRepository.findExamBySchoolYear(query.year()).map(this::getExam);
    }

    private ExamResponse getExam(Exam exam) {
        return new ExamResponse(exam.getExamId(), exam.getName(),
                exam.getCode(), exam.getProfFullname(), exam.getProfCode(),
                exam.getNoteMax(), exam.getCourseName(), exam.getCourseCode(),
                exam.getEstablishmentName(), exam.getEstablishmentCode(),
                exam.getSectionName(), exam.getSectionCode(), exam.getClassroomName(),
                exam.getClassroomCode(), exam.getTrimester(), exam.getSchoolYear(),
                exam.getLogCreatedAt(), exam.getLogCreatedDate(),
                exam.getLogCreatedMonth(), exam.getLogCreatedYear());
    }
}
