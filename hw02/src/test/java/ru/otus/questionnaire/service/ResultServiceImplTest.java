package ru.otus.questionnaire.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.questionnaire.config.TestConfig;
import ru.otus.questionnaire.domain.Answer;
import ru.otus.questionnaire.domain.Result;
import ru.otus.questionnaire.domain.Student;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@DisplayName("Class ResultServiceImplTest")
@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    @Mock
    private ConsoleIOServiceImpl ioService;

    @Mock
    private TestConfig testConfig;

    private ResultService resultService;

    private Result failedResult;

    private Result passedResult;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        resultService = new ResultServiceImpl(ioService, testConfig);
        failedResult = new Result(new Student("Artem", "Kobyakov"));
        failedResult.applyAnswer(new Answer("JVM", true));

        passedResult = new Result(new Student("Artem", "Kobyakov"));
        passedResult.applyAnswer(new Answer("Some text", true));
        passedResult.applyAnswer(new Answer("Some text", true));
    }

    @DisplayName("should print failed result of test")
    @Test
    void shouldPrintFailedResultOfTest() {
        String expectedResultString = """

                YOUR RESULT

                Artem Kobyakov
                COUNT OF QUESTIONS: 1
                COUNT OF RIGHT ANSWERS: 1
                SORRY! YOU FAILED TEST
                """;
        when(testConfig.getCountAnsweredQuestionsForPass()).thenReturn(3);
        doCallRealMethod().when(ioService).println(anyString());
        resultService.showResult(failedResult);

        assertEquals(expectedResultString, outContent.toString());
    }


    @DisplayName("should print passed result of test")
    @Test
    void shouldPrintPassedResultOfTest() {
        String expectedResultString = """

                YOUR RESULT

                Artem Kobyakov
                COUNT OF QUESTIONS: 2
                COUNT OF RIGHT ANSWERS: 2
                CONGRATULATIONS! YOU PASSED TEST
                """;
        when(testConfig.getCountAnsweredQuestionsForPass()).thenReturn(2);
        doCallRealMethod().when(ioService).println(anyString());
        resultService.showResult(passedResult);

        assertEquals(expectedResultString, outContent.toString());
    }
}