package ru.otus.questionnaire.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Question {

    private final String question;

    private final List<Answer> answerOptions;
}


