package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.domain.Question;

@Service
@RequiredArgsConstructor
public class QuestionPrinterImpl implements QuestionPrinter {

    public static final String DASH = " - ";
    private final IOService ioService;

    @Override
    public void printQuestion(Question question) {
        ioService.println(question.getQuestion());
        ioService.printEmptyLine();
        question.getAnswerOptions()
                .forEach(answerOption -> ioService.println(DASH + answerOption.getAnswer()));
    }

}
