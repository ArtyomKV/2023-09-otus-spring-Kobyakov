package ru.otus.questionnaire.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "application.properties")
public class AppConfig implements FileNameProvider {

    @Value("${file.name}")
    private String testFileName;

    @Override
    public String getTestFileName() {
        System.out.println("FILE NAME IS" + testFileName);
        return testFileName;
    }
}
