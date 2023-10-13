package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.domain.Question;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class QuestionPrinterImpl implements QuestionPrinter {

    public static final String COLON_SYMBOL = ": ";

    public static final String INDENT = "   ";

    public static final int FIRST_INDEX = 0;

    public static final int INCREMENT_VALUE_FOR_QUESTION_NUMBER = 1;

    private final IOService ioService;

    @Override
    public void printQuestions(List<Question> questions) {

        IntStream.range(FIRST_INDEX, questions.size()).forEach(index -> {
            ioService.println(getQuestionNumber(index) + COLON_SYMBOL + questions.get(index).getQuestion());
            questions.get(index).getAnswerOptions()
                    .forEach(answerOption -> ioService.println(INDENT + answerOption.getAnswer()));
        });
    }

    private int getQuestionNumber(int index) {
        return index + INCREMENT_VALUE_FOR_QUESTION_NUMBER;
    }

}
