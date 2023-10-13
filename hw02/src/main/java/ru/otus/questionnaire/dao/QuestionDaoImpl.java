package ru.otus.questionnaire.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.questionnaire.domain.AnswerOption;
import ru.otus.questionnaire.domain.Question;
import ru.otus.questionnaire.exception.QuestionsAccessException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    public static final String COMMA_DELIMITER = ",";

    private static final String FILE_NAME = "questions.csv";

    public static final int ANSWER_COLUMN_NUMBER = 0;

    public static final int ANSWER_OPTION_COLUMN_NUMBER = 1;

    public static final int CORRECT_FLAG_COLUMN_NUMBER = 2;


    @Override
    public List<Question> findAll() {
        return getQuestionsAsResources();
    }

    private List<Question> getQuestionsAsResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream questionsInputStream = classLoader.getResourceAsStream(FILE_NAME);
        return getQuestionsFromInputStream(questionsInputStream);
    }

    private List<Question> getQuestionsFromInputStream(InputStream questionsInputStream) {
        List<Question> questions;
        try (InputStreamReader streamReader = new InputStreamReader(questionsInputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            Map<String, List<AnswerOption>> questionAnswers = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                fillMapByLine(line, questionAnswers);
            }
            questions = getQuestions(questionAnswers);
        } catch (IOException e) {
            throw new QuestionsAccessException("Can not to get Questions!", e);
        }
        return questions;
    }

    private void fillMapByLine(String line, Map<String, List<AnswerOption>> questionAnswers) {
        String[] result = line.split(COMMA_DELIMITER);
        String question = result[ANSWER_COLUMN_NUMBER];
        AnswerOption answerOption = getAnswerOption(result);
        if (questionAnswers.containsKey(question)) {
            questionAnswers.get(question).add(answerOption);
        } else {
            List<AnswerOption> answerOptions = new ArrayList<>();
            answerOptions.add(answerOption);
            questionAnswers.put(question, answerOptions);
        }
    }

    private AnswerOption getAnswerOption(String[] result) {
        String answer = result[ANSWER_OPTION_COLUMN_NUMBER];
        boolean correctFlag = Boolean.parseBoolean(result[CORRECT_FLAG_COLUMN_NUMBER]);
        return new AnswerOption(answer, correctFlag);
    }

    private List<Question> getQuestions(Map<String, List<AnswerOption>> questionAnswers) {
        List<Question> questions = new ArrayList<>();
        questionAnswers.forEach((k, v) -> questions.add(new Question(k, v)));
        return questions;
    }
}