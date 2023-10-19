package ru.otus.questionnaire.domain;

import lombok.Data;

@Data
public class Result {

    private Student student;

    private int countOfQuestion;

    private int countRightAnswers;

    public Result(Student student) {
        this.student = student;
    }

    public void applyAnswer(Answer answer) {
        countOfQuestion++;
        if (answer.isCorrect()) {
            countRightAnswers++;
        }
    }
}
