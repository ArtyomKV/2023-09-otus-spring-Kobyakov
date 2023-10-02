package ru.otus.questionnaire.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnswerOption {

    private final String answer;

    private final boolean correct;
}
