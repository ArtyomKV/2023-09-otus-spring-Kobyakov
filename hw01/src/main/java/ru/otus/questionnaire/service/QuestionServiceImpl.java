package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import ru.otus.questionnaire.dao.QuestionDao;
import ru.otus.questionnaire.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    private final QuestionPrinter printer;

    @Override
    public void printAllQuestions() {
        List<Question> questions = getAllQuestions();
        printer.printQuestions(questions);
    }

    private List<Question> getAllQuestions() {
        return questionDao.findAll();
    }
}
