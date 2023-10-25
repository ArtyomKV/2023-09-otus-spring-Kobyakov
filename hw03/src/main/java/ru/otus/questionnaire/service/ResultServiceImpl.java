package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.config.TestConfig;
import ru.otus.questionnaire.domain.Result;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final LocalizedIOService ioService;

    private final TestConfig testConfig;

    @Override
    public void showResult(Result result) {
        ioService.printEmptyLine();
        ioService.printLocalizedLine("ResultService.your.result", result.getStudent().getFullName());
        ioService.printLocalizedLine("ResultService.count.of.questions", result.getCountOfQuestion());
        ioService.printLocalizedLine("ResultService.count.of.right.answers", result.getCountRightAnswers());
        if (result.getCountRightAnswers() >= testConfig.getCountAnsweredQuestionsForPass()) {
            ioService.printLocalizedLine("ResultService.congratulation.pass");
            return;
        }
        ioService.printLocalizedLine("ResultService.failed.pass");
    }
}
