package ru.otus.questionnaire.service;

import org.springframework.stereotype.Service;

@Service
public class ConsoleIOServiceImpl implements IOService {

    @Override
    public void println(String stringForPrint) {
        System.out.println(stringForPrint);
    }
}