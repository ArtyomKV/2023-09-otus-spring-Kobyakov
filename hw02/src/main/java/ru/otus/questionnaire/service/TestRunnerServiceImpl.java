package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.domain.Student;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {

    private final StudentService studentService;

    private final QuestionService questionService;

    @Override
    public void run() {
        Student student = studentService.getStudent();
        System.out.println("Test for " + student);
        questionService.printAllQuestions();
    }
}
