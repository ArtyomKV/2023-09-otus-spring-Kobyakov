package ru.otus.questionnaire.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleIOServiceImpl implements IOService {

    private final Scanner scanner;

    public ConsoleIOServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void println(String stringForPrint) {
        System.out.println(stringForPrint);
    }

    @Override
    public String getLineByQuestion(String lineForPrint) {
        System.out.println(lineForPrint);
        return scanner.nextLine();
    }

    @Override
    public void printEmptyLine() {
        System.out.println();
    }
}