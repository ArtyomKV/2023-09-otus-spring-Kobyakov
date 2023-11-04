package ru.otus.questionnaire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.questionnaire.domain.Result;
import ru.otus.questionnaire.domain.Student;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements CommandLineRunner {

    private final StudentService studentService;

    private final TestService testService;

    private final ResultService resultService;

    @Override
    public void run(String... args) {
        Student student = studentService.getStudent();
        Result result = testService.doTest(student);
        resultService.showResult(result);
    }
}
