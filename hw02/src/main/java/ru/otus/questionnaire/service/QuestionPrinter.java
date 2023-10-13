package ru.otus.questionnaire.service;

import ru.otus.questionnaire.domain.Question;

import java.util.List;

public interface QuestionPrinter {

    void printQuestions(List<Question> questions);
}
