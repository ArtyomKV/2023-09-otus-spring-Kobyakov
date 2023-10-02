package ru.otus.questionnaire.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.questionnaire.domain.Question;
import ru.otus.questionnaire.exception.QuestionsAccessException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final String fileName;

    @Override
    public List<Question> findAll() {
        return getQuestionsAsResources();
    }

    private List<Question> getQuestionsAsResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream questionsInputStream = classLoader.getResourceAsStream(fileName);
        return getQuestionsFromInputStream(questionsInputStream);
    }

    private List<Question> getQuestionsFromInputStream(InputStream questionsInputStream) {
        List<Question> questions = new ArrayList<>();
        try (InputStreamReader streamReader = new InputStreamReader(questionsInputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Question question = new Question(line);
                questions.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new QuestionsAccessException(e.getMessage());
        }
        return questions;
    }
}