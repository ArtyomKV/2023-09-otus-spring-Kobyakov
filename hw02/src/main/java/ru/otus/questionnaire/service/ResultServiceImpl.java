package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.domain.Result;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private static final String RESULT_SPLIT_STRING = "\nYOUR RESULT\n";
    private static final String RIGHT_ANSWERS_STRING = "COUNT OF RIGHT ANSWERS: ";
    private final IOService ioService;

    @Override
    public void showResult(Result result) {
        ioService.println(RESULT_SPLIT_STRING);
        ioService.println(result.getStudent().toString());
        ioService.println(RIGHT_ANSWERS_STRING + result.getCountRightAnswers());
    }
}
