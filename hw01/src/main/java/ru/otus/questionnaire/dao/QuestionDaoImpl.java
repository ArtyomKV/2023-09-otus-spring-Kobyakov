package ru.otus.questionnaire.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.questionnaire.domain.Question;

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
        InputStream fileInputStream = getInputStreamFromFile();
        return getQuestionsFromInputStream(fileInputStream);
    }

    private InputStream getInputStreamFromFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    private List<Question> getQuestionsFromInputStream(InputStream fileInputStream) {
        List<Question> questions = new ArrayList<>();
        try (InputStreamReader streamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Question question = new Question(line);
                questions.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}