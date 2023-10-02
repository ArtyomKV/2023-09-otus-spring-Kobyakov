package ru.otus.questionare.service;

import org.junit.jupiter.api.*;
import ru.otus.questionnaire.domain.Question;
import ru.otus.questionnaire.service.ConsoleIOServiceImpl;
import ru.otus.questionnaire.service.QuestionPrinterImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Class QuestionPrinterImpl")
class QuestionPrinterImplTest {

    public static final String FIRST_QUESTION = "first question";
    public static final String SECOND_QUESTION = "second question";
    public static final String NEXT_LINE_SYMBOL = "\n";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final QuestionPrinterImpl questionPrinter = new QuestionPrinterImpl(new ConsoleIOServiceImpl());
    private final List<Question> questions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        Question firstQuestion = new Question(FIRST_QUESTION);
        Question secondQuestion = new Question(SECOND_QUESTION);
        questions.add(firstQuestion);
        questions.add(secondQuestion);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @DisplayName("should print questions correctly")
    @Test
    void shouldPrintQuestionsCorrectly() {
        String expectedResult = FIRST_QUESTION + NEXT_LINE_SYMBOL + SECOND_QUESTION + NEXT_LINE_SYMBOL;
        questionPrinter.printQuestions(questions);
        assertEquals(expectedResult, outContent.toString());
    }
}