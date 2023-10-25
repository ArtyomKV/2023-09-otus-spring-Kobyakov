package ru.otus.questionnaire.service;

public interface LocalizedIOService extends IOService {

    String getLineByLocalizedQuestion(String code);

    void printLocalizedLine(String code, Object... args);
}
