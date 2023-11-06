package ru.otus.questionnaire.service;

public interface LocalizationMessageService {

    String getLocalizedMessage(String questionCode, Object... args);
}
