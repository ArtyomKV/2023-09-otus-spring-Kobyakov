package ru.otus.questionnaire.service;

public class ConsoleIOServiceImpl implements IOService {

    @Override
    public void println(String stringForPrint) {
        System.out.println(stringForPrint);
    }
}