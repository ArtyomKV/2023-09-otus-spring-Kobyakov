package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.config.TestConfig;
import ru.otus.questionnaire.domain.Result;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private static final String RESULT_SPLIT_STRING = "\nYOUR RESULT\n";

    private static final String COUNT_OF_QUESTIONS = "COUNT OF QUESTIONS: ";

    private static final String RIGHT_ANSWERS_STRING = "COUNT OF RIGHT ANSWERS: ";

    private static final String PASSED_TEST_STRING = "CONGRATULATIONS! YOU PASSED TEST";

    private static final String FAILED_TEST_STRING = "SORRY! YOU FAILED TEST";

    private final IOService ioService;

    private final TestConfig testConfig;

    @Override
    public void showResult(Result result) {
        ioService.println(RESULT_SPLIT_STRING);
        ioService.println(result.getStudent().getFullName());
        ioService.println(COUNT_OF_QUESTIONS + result.getCountOfQuestion());
        ioService.println(RIGHT_ANSWERS_STRING + result.getCountRightAnswers());
        if (result.getCountRightAnswers() >= testConfig.getCountAnsweredQuestionsForPass()) {
            ioService.println(PASSED_TEST_STRING);
            return;
        }
        ioService.println(FAILED_TEST_STRING);
    }
}
