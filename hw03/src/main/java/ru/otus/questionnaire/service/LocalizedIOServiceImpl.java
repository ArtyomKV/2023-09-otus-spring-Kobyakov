package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalizedIOServiceImpl implements LocalizedIOService {

    private final LocalizationMessageService localizationMessageService;

    private final IOService ioService;

    @Override
    public void println(String stringForPrint) {
        ioService.println(stringForPrint);
    }

    @Override
    public String getLineByQuestion(String lineForPrint) {
        return ioService.getLineByQuestion(lineForPrint);
    }

    @Override
    public void printEmptyLine() {
        ioService.printEmptyLine();
    }

    @Override
    public String getLineByLocalizedQuestion(String code) {
        return ioService.getLineByQuestion(localizationMessageService.getLocalizedMessage(code));
    }

    @Override
    public void printLocalizedLine(String code, Object... args) {
        ioService.println(localizationMessageService.getLocalizedMessage(code, args));
    }

}
