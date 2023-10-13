package ru.otus.questionnaire.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleIOServiceImpl implements IOService {

    @Override
    public void println(String stringForPrint) {
        System.out.println(stringForPrint);
    }

    @Override
    public String getLineByQuestion(String lineForPrint) {
        System.out.println(lineForPrint);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}