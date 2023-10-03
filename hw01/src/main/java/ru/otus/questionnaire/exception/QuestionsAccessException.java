package ru.otus.questionnaire.exception;

public class QuestionsAccessException extends RuntimeException {

    public QuestionsAccessException(String message, Throwable original) {
        super(message, original);
    }
}
