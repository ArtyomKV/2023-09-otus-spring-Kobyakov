package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.dao.QuestionDao;
import ru.otus.questionnaire.domain.Answer;
import ru.otus.questionnaire.domain.Question;
import ru.otus.questionnaire.domain.Result;
import ru.otus.questionnaire.domain.Student;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    public static final String CALL_TO_ACTION_STRING = " choose right answers for questions below: ".toUpperCase();

    private final QuestionDao questionDao;

    private final QuestionPrinter questionPrinter;

    private final IOService ioService;

    @Override
    public Result doTest(Student student) {
        List<Question> questions = questionDao.findAll();
        printCallToAction(student);
        return askAllQuestions(student, questions);
    }

    private void printCallToAction(Student student) {
        ioService.printEmptyLine();
        ioService.println(student.getFullName().toUpperCase() + CALL_TO_ACTION_STRING);
        ioService.printEmptyLine();
    }

    private Result askAllQuestions(Student student, List<Question> questions) {
        Result result = new Result(student);
        for (Question question : questions) {
            questionPrinter.printQuestion(question);
            Answer answer = getAnswer(question);
            result.applyAnswer(answer);
        }
        return result;
    }

    private Answer getAnswer(Question question) {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        boolean answerIsCorrect = answerIsCorrect(question, answer);
        return new Answer(answer, answerIsCorrect);
    }

    private boolean answerIsCorrect(Question question, String answer) {
        return question.getAnswerOptions()
                .stream()
                .anyMatch(answerOption -> answerOption.getAnswer().equalsIgnoreCase(answer));
    }
}
