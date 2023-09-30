package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import ru.otus.questionnaire.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionPrinterImpl implements QuestionPrinter {

    @Override
    public void printQuestions(List<Question> questions) {
        questions.forEach(question -> System.out.println(question.getQuestion()));
    }
}
