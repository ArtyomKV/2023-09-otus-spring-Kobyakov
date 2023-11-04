package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.config.LocaleProvider;

@Service
@RequiredArgsConstructor
public class LocalizationMessageServiceImpl implements LocalizationMessageService {

    private final MessageSource messageSource;

    private final LocaleProvider localeProvider;

    @Override
    public String getLocalizedMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, localeProvider.getLocale());
    }
}