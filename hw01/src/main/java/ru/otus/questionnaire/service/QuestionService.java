package ru.otus.questionnaire.service;

import ru.otus.questionnaire.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    void printAllQuestions(List<Question> questions);
}
