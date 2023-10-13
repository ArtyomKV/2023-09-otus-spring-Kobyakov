package ru.otus.questionnaire.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {

    private Student student;

    private int countRightAnswers;

}
