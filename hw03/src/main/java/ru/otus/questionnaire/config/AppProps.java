package ru.otus.questionnaire.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "test")
public class AppProps implements FileNameProvider, TestConfig, LocaleProvider {

    private final int countAnsweredQuestionsForPass;

    private final Locale locale;

    private final Map<String, String> fileNameByLocaleTag;

    @ConstructorBinding
    public AppProps(int countAnsweredQuestionsForPass, Locale locale, Map<String, String> fileNameByLocaleTag) {
        this.countAnsweredQuestionsForPass = countAnsweredQuestionsForPass;
        this.locale = locale;
        this.fileNameByLocaleTag = fileNameByLocaleTag;
    }

    @Override
    public int getCountAnsweredQuestionsForPass() {
        return countAnsweredQuestionsForPass;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public String getTestFileName() {
        return fileNameByLocaleTag.get(locale.toLanguageTag());
    }
}
