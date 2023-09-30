package ru.otus.questionnaire.dao;

import ru.otus.questionnaire.domain.Question;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private static final String FILE_NAME = "questions.csv";

    @Override
    public List<Question> findAll() {
        //todo: реализовать логику вычитки файла как ресурса
        return null;
    }
}
