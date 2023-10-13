package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.dao.QuestionDao;
import ru.otus.questionnaire.domain.Question;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    private final QuestionPrinter printer;

    @Override
    public void printAllQuestions() {
        List<Question> questions = questionDao.findAll();
        printer.printQuestions(questions);
    }
}
