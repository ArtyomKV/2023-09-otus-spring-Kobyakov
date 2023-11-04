package ru.otus.questionnaire.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.questionnaire.config.FileNameProvider;
import ru.otus.questionnaire.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = QuestionDaoImpl.class)
class QuestionDaoImplTest {

    @Autowired
    private QuestionDao questionDao;

    @MockBean
    private FileNameProvider fileNameProvider;

    @Test
    void findAll() {
        Mockito.when(fileNameProvider.getTestFileName()).thenReturn("questions_ru.csv");
        List<Question> questions = questionDao.findAll();
        assertEquals(5, questions.size());
    }
}