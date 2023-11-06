package ru.otus.questionnaire.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.questionnaire.config.LocaleProvider;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LocalizationMessageService ")
@SpringBootTest
class LocalizationMessageServiceImplTest {

    @Autowired
    private LocalizationMessageService localizationMessageService;

    @DisplayName(" should return correct greetings for ru_RU locale")
    @Test
    void getLocalizedMessage() {
        String expectedString = "Добро пожаловать!";
        String localizedMessage = localizationMessageService.getLocalizedMessage("ApplicationShellRunner.greetings");
        assertEquals(expectedString, localizedMessage);
    }
}