package ru.otus.questionnaire.dao;

import ru.otus.questionnaire.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private static final String FILE_NAME = "questions.csv";

    @Override
    public List<Question> findAll() {
        //todo: реализовать логику вычитки файла как ресурса
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question 1"));
        questions.add(new Question("Question 2"));
        questions.add(new Question("Question 3"));
        return questions;
    }
}
