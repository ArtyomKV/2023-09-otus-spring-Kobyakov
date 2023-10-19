package ru.otus.questionnaire.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "application.properties")
public class AppConfig implements FileNameProvider, TestConfig {

    @Value("${test.file.name}")
    private String testFileName;

    @Value("${test.countAnsweredQuestionsForPass}")
    private int countAnsweredQuestionsForPass;

    @Override
    public String getTestFileName() {
        return testFileName;
    }


    @Override
    public int getCountAnsweredQuestionsForPass() {
        return countAnsweredQuestionsForPass;
    }
}
