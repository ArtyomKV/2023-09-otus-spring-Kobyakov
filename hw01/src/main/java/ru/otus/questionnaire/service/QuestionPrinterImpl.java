package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import ru.otus.questionnaire.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionPrinterImpl implements QuestionPrinter {

    private final IOService ioService;

    @Override
    public void printQuestions(List<Question> questions) {
        questions.forEach(question -> ioService.println(question.getQuestion()));
    }
}
