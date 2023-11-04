package ru.otus.questionnaire.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import ru.otus.questionnaire.config.FileNameProvider;
import ru.otus.questionnaire.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Class QuestionDaoImpl")
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations="classpath:test.properties")
class QuestionDaoImplTest {

    @Mock
    private FileNameProvider fileNameProvider;

    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        questionDao = new QuestionDaoImpl(fileNameProvider);
    }

    @DisplayName("should find all questions")
    @Test
    void findAll() {
        int expectedCountOfQuestions = 4;
        when(fileNameProvider.getTestFileName()).thenReturn("questions.csv");
        List<Question> all = questionDao.findAll();
        assertEquals(expectedCountOfQuestions, all.size());
    }
}