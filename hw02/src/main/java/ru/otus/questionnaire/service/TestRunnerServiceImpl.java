package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.domain.Result;
import ru.otus.questionnaire.domain.Student;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {

    private final StudentService studentService;

    private final TestService testService;

    private final ResultService resultService;

    @Override
    public void run() {
        Student student = studentService.getStudent();
        System.out.println("Test for " + student);
        Result result = testService.doTest(student);
        resultService.showResult(result);
    }
}
