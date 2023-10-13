package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.dao.QuestionDao;
import ru.otus.questionnaire.domain.Question;
import ru.otus.questionnaire.domain.Result;
import ru.otus.questionnaire.domain.Student;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    private final QuestionPrinter printer;

    @Override
    public Result doTest(Student student) {
        List<Question> questions = questionDao.findAll();
        printer.printQuestions(questions);
        return new Result(student, 0);
    }
}
